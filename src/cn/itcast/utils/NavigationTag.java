package cn.itcast.utils;

import cn.itcast.utils.Page;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class NavigationTag extends TagSupport {
	static final long serialVersionUID = 2372405317744358833L;
	private String bean = "page";
	private String url = null;
	private int number = 5;

	public int doStartTag() {
		JspWriter writer = this.pageContext.getOut();
		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		Page<?> page = (Page<?>) request.getAttribute(this.bean);
		if (page == null) {
			return 0;
		} else {
			try {
				this.url = this.resolveUrl(this.url, this.pageContext);
			} catch (Exception arg7) {
				arg7.printStackTrace();
			}

			try {
				int e = page.getTotal() / page.getSize();
				if (page.getTotal() % page.getSize() > 0) {
					++e;
				}

				writer.print("<nav><ul class=\"pagination\">");
				if (page.getPage() > 1) {
					String indexPage = this.append(this.url, "page", page.getPage() - 1);
					indexPage = this.append(indexPage, "rows", page.getSize());
					writer.print("<li><a href=\"" + indexPage + "\">上一页</a></li>");
				} else {
					writer.print("<li class=\"disabled\"><a href=\"#\">上一页</a></li>");
				}

				int arg9 = page.getPage() - 2 > 0 ? page.getPage() - 2 : 1;

				for (int nextUrl = 1; nextUrl <= this.number && arg9 <= e; ++nextUrl) {
					if (arg9 == page.getPage()) {
						writer.print("<li class=\"active\"><a href=\"#\">" + arg9
								+ "<span class=\"sr-only\">(current)</span></a></li>");
					} else {
						String pageUrl = this.append(this.url, "page", arg9);
						pageUrl = this.append(pageUrl, "rows", page.getSize());
						writer.print("<li><a href=\"" + pageUrl + "\">" + arg9 + "</a></li>");
					}

					++arg9;
				}

				if (page.getPage() < e) {
					String arg10 = this.append(this.url, "page", page.getPage() + 1);
					arg10 = this.append(arg10, "rows", page.getSize());
					writer.print("<li><a href=\"" + arg10 + "\">下一页</a></li>");
				} else {
					writer.print("<li class=\"disabled\"><a href=\"#\">下一页</a></li>");
				}

				writer.print("</nav>");
			} catch (IOException arg8) {
				arg8.printStackTrace();
			}

			return 0;
		}
	}

	private String append(String url, String key, int value) {
		return this.append(url, key, String.valueOf(value));
	}

	private String append(String url, String key, String value) {
		if (url != null && url.trim().length() != 0) {
			if (url.indexOf("?") == -1) {
				url = url + "?" + key + "=" + value;
			} else if (url.endsWith("?")) {
				url = url + key + "=" + value;
			} else {
				url = url + "&amp;" + key + "=" + value;
			}

			return url;
		} else {
			return "";
		}
	}

	private String resolveUrl(String url, PageContext pageContext) throws Exception {
		if (url != null) {
			url = new String(url.getBytes("iso8859-1"), "utf-8");
		}

		Map<String, String[]> params = pageContext.getRequest().getParameterMap();
		Iterator<String> arg4 = params.keySet().iterator();

		while (arg4.hasNext()) {
			Object key = arg4.next();
			if (!"page".equals(key) && !"rows".equals(key)) {
				Object value = params.get(key);
				if (value != null) {
					if (value.getClass().isArray()) {
						url = this.append(url, key.toString(),
								new String(((String[]) value)[0].getBytes("iso8859-1"), "utf-8"));
					} else if (value instanceof String) {
						url = this.append(url, key.toString(),
								new String(value.toString().getBytes("iso8859-1"), "utf-8"));
					}
				}
			}
		}

		return url;
	}

	public String getBean() {
		return this.bean;
	}

	public void setBean(String bean) {
		this.bean = bean;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}