package com.melawai.ppuc.web.interceptor;

import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.melawai.ppuc.model.Menu;
import com.melawai.ppuc.model.User;
import com.melawai.ppuc.services.UserManager;
import com.melawai.ppuc.utils.CommonUtil;

/**
 * 
 * @author : Bertho Rafitya Iwasurya
 * @since : Oct 25, 2013 2:30:41 PM
 * @Description : Interceptor untuk meng-intercept request misalnya user yg
 *              belum login, etc
 * @Revision :
 *           #====#===========#===================#===========================#
 *           | ID | Date | User | Description |
 *           #====#===========#===================#===========================#
 *           | | | | |
 *           #====#===========#===================#===========================#
 */
public class MainInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private Properties props;

	@Autowired
	private UserManager userManager;

	private static Logger logger = Logger.getLogger(MainInterceptor.class);

	public void setProps(Properties props) {
		this.props = props;
	}

	private final String[] bolehLewat = new String[] { "/resources","/static","/login","/json","/403","/uncaughtException","/resourceNotFound","/dataAccessFailure", "/welcome", "/main", "/logout" };

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();

		request.setAttribute("ver", props.getProperty("app.version"));
		
		// bypass menu
		for (String boleh : bolehLewat) {
			if (uri.contains(boleh)) {
				return true;
			}
		}
		
		if (request.getSession().getAttribute("leftmenu") == null){
			User currentUser = CommonUtil.getCurrentUser();
			if (currentUser != null) {
				request.getSession().removeAttribute("leftmenu");
				
				request.getSession().setAttribute("leftmenu", userManager.generateMenu(request.getContextPath(), CommonUtil.getCurrentUser()));
			}
		}

		
		if(uri.endsWith(request.getContextPath()+"/" ))return true;
		
		

		// cek apakah user sudah ada session atau belum
		HttpSession session = request.getSession(false); // kenapa false? karena
		// kalau tidak, dia
		// akan selalu create
		// session baru, makan
		// memory
		if (session != null) {
			// cek user logged in
			User currentUser = CommonUtil.getCurrentUser();
			if (currentUser != null) {// kalau sudah login masuk sini
				
				Set<Menu> lMenu = currentUser.getGroupUser().getMenus();

				if (lMenu != null) {// cek menu ada atau ga
					boolean ada = false;

					/*if (uri.contains("/report")) { // kalau path report baca
						// dulu nama reportnya apa
						String reportName = (String) request.getParameter("fname");
						if (CommonUtil.isEmpty(reportName))
							reportName = (String) request.getParameter("_R_reportName");

						for (Menu b : lMenu) {
							String url = b.getLink();
							if (url == null)
								continue;
							if (url.contains(reportName)) {
								ada = true;
								break;
							}
						}
					} else {*/
						for (Menu b : lMenu) {
							String url = b.getLink();
							if (url == null)
								continue;
							if (uri.contains(url)) {
								ada = true;
								break;
							}
						}
//					}
						
					if (!ada) {// kalau tidak ada otorisasi tendang ke 403
						response.sendRedirect(request.getContextPath() + "/403");
						return false;
					}
				}
				return true;
			} else {
				return true;
			}
		} else {
			return true;
		}

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.debug("postHandle: " + handler);

		super.postHandle(request, response, handler, modelAndView);
	}

}