package com.perfmath.spring.soba.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Iterator;

import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import java.util.Enumeration;
import java.util.List;

import com.perfmath.spring.soba.util.MyLogger;

@Controller
@RequestMapping("/log4jConsole")
@SessionAttributes("log4j")
public class Log4jConsoleController extends AbstractController {

	@RequestMapping(value="/log4jConsole", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String loggerSelected = request.getParameter("loggerSelected");
		if (loggerSelected != null && loggerSelected.length() > 0) {
			String levelSelected = request.getParameter("levelSelected");
			setLogLevel(loggerSelected, levelSelected);
		}
		
		ModelAndView mav = new ModelAndView ();
		mav.setViewName("log4jConsole");
		String name = "";
		String effectiveLevel = "";
		String parent = "";
		Map<String, Object> model = new HashMap<String, Object> ();
		Enumeration loggers = LogManager.getCurrentLoggers();

		SortedMap sm = new TreeMap();
		
		while (loggers.hasMoreElements()) {	
			Logger logger = (Logger) loggers.nextElement();
			name = logger.getName().toString();
			effectiveLevel = logger.getEffectiveLevel().toString();
			parent = logger.getParent().getName().toString();
			if (name.startsWith("com.perfmath")) {
				sm.put (name, new MyLogger (name, effectiveLevel,parent));
			}
		}
		
		List<MyLogger> myLoggers = new ArrayList<MyLogger>();
		Iterator it = sm.keySet().iterator();
		while (it.hasNext() ) {
			Object key = it.next();
			myLoggers.add ((MyLogger) sm.get(key));
		}
		model.put ("myLoggers", myLoggers);
		mav.addObject ("model", model);
		return new ModelAndView("log4jConsole", model);
	}
	public void setLogLevel(String loggerSelected, String levelSelected) {
		Logger rootLogger = Logger.getRootLogger();
		Logger logger = rootLogger.getLoggerRepository().getLogger(loggerSelected);
		logger.setLevel((Level)Level.toLevel(levelSelected));
	}
}

