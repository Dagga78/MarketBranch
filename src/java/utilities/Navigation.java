package utilities;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.AbstractDocument.Content;

public class Navigation {

	private static String lastPage;
	private static String actualPage;

	/**
	 * Charge pour la servlet la page donn�e en param�tre
	 * 
	 * @param request  Requ�te du client
	 * @param response R�ponse du serveur
	 * @param jsp      Fichier JSP � charger
	 * 
	 */
	public static void load(HttpServletRequest request, HttpServletResponse response, String jsp) {
		try {
			System.out.println("load : " + jsp);
			request.getRequestDispatcher("/JSP/" + jsp + ".jsp").forward(request, response);
			return;
		} catch (Exception e) {
			System.err.println("Navigation.load : " + e);
		}
	}

	public static void menu(HttpServletRequest request, HttpServletResponse response, String button, String lastPage) {
		HttpSession session = request.getSession();
		if (null != button) {
			switch (button) {
			case "home":
				Navigation.to(request, response, "home");
				return;
			case "listExpos":
				Navigation.to(request, response, "listExpos");
				return;
			case "account":
				Navigation.to(request, response, "account");
				return;
			case "addExpo":
				Navigation.to(request, response, "addExpo");
				return;
			case "quizzInfo":
				session.setAttribute("idQuizz", request.getParameter("idQuizz"));
				Navigation.to(request, response, "quizzInfo");
				return;
			case "oeuvreInfo":
				session.setAttribute("idOeuvre", request.getParameter("idOeuvre"));
				Navigation.to(request, response, "oeuvreInfo");
				return;
			case "expo":
				session.setAttribute("expo", request.getParameter("expo"));
				Navigation.to(request, response, "expoInfo");
				return;
			case "users":
				Navigation.to(request, response, "users");
				return;
			case "userInfo":
				session.setAttribute("idUser", request.getParameter("idUser"));
				Navigation.to(request, response, "userInfo");
				return;
			case "espaceCulturelInfo":
				session.setAttribute("idEspaceCulturel", request.getParameter("idEspaceCulturel"));
				Navigation.to(request, response, "espaceCulturelInfo");
				return;
			case "signin":
				session.setAttribute("idUserConnected", request.getParameter("idUserSignin"));
				Navigation.to(request, response, "home");
				return;
			case "disconnect":
				Navigation.to(request, response, "signin");
				request.getSession().invalidate();
				return;
			default:
				Navigation.load(request, response, lastPage);
				return;
			}
		}
	}

	/**
	 * V�rifie le navigateur web via {@link Content#checkBrowser} <br>
	 * V�rifie le param�tre "exist" de la session: <br>
	 * - Si il est null c'est que la session n'a pas �t� cr��e, on redirige donc
	 * vers signin <br>
	 * - Sinon on renvoie false
	 * 
	 * @param request  Requ�te du client
	 * @param response R�ponse du serveur
	 * @return true si l'utilisateur n'est pas connect�
	 */
	public static boolean checkSession(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute("idUserConnected") == null) {
			request.getSession().invalidate();
			Navigation.to(request, response, "signin");
			return false;
		}
		return true;
	}

	/**
	 * Redirige vers la page donn�e en param�tre
	 * 
	 * @param request  Requ�te du client
	 * @param response R�ponse du serveur
	 * @param page     Page vers laquelle rediriger
	 */
	public static void to(HttpServletRequest request, HttpServletResponse response, String page) {
		try {
			lastPage = actualPage;
			actualPage = page;
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/" + page));
			System.out.println("to : " + page);
		} catch (Exception e) {
			System.err.println("Navigation.to : " + page + "  " + e);
		}
	}

	/**
	 * Redirige vers l'URL donn�e en param�tre
	 * 
	 * @param request  Requ�te du client
	 * @param response R�ponse du serveur
	 * @param url      URL vers laquelle rediriger (format : nomapplication/page)
	 */
	public static void toURL(HttpServletRequest request, HttpServletResponse response, String url) {
		try {
			response.sendRedirect(
					request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Rediriges vers la derni�re page visit�e
	 * 
	 * @param request  Requ�te du client
	 * @param response R�ponse du serveur
	 * @return true si la redirection c'est effectu� correctement
	 */
	public static void back(HttpServletRequest request, HttpServletResponse response) {
		if (lastPage != null)
			to(request, response, lastPage);
		else
			to(request, response, "welcome");
	}
}
