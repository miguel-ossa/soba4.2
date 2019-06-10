package com.perfmath.spring.soba.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import com.perfmath.spring.soba.util.PerfBasicUtil;

@Controller
@RequestMapping("/perfBasicConsole")
@SessionAttributes("perfBasic")
public class PerfBasicConsoleController extends AbstractController {
	@RequestMapping(value="/perfBasicConsole", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String profilingStatus = request.getParameter("profilingStatus");
		if (profilingStatus != null ) {
			resetProfiling(profilingStatus);
		}

		ModelAndView mav = new ModelAndView ();
		mav.setViewName("perfBasicConsole");

		Map<String, Object> model = new HashMap<String, Object> ();

		model.put ("profilingStatus", PerfBasicUtil.getProfilingStatus());
		mav.addObject ("model", model);
		return new ModelAndView("perfBasicConsole", model);
	}
	public void resetProfiling(String profilingStatus) {
		if (!PerfBasicUtil.getProfilingStatus().equals(profilingStatus)) {
			PerfBasicUtil.setProfilingStatus(profilingStatus);
			if (PerfBasicUtil.getProfilingStatus().equals("disabled")){
				PerfBasicUtil.flushApfWriter();
			}
		} 
	}
}

