package com.perfmath.spring.soba.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
@RequestMapping("/loginBroker")
@SessionAttributes("login")
public class LoginBroker extends AbstractController {
	private Log log = LogFactory.getLog(this.getClass());
	private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();
		mav = getModelAndView(request);
		return mav;
	}

	public ModelAndView getModelAndView(HttpServletRequest request) {
		log.info("+");
		logger.info("Testing logging from SLF4J ... it works");
		String authority = SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities().toString();
		logger.info("authority: " + authority.toString());
		String viewString = "";
		if (authority.contains("ADMIN")) {
			viewString = "adminConsole";
		} else if (authority.contains("REP")) {
			viewString = "repConsole";
		} else if (authority.contains("CUST")) {
			viewString = "redirect:activityList";
		}
		log.info("-");
		return new ModelAndView(viewString);
	}

}
