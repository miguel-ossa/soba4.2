package com.perfmath.spring.soba.web;
import com.perfmath.spring.soba.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
@Controller
@RequestMapping("/logoff")
@SessionAttributes("login")
public class LogoffController extends AbstractController {
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession (false);
		if (session != null) {
			session.invalidate();

			SecurityContextHolder.getContext()
				.getAuthentication().setAuthenticated(false);
			SecurityContextHolder.clearContext();
		}
		return new ModelAndView ("redirect:logoff.jsp");
	}

	
}

