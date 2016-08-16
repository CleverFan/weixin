package com.chengfan.ycjw.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.chengfan.ycjw.po.StudentTable;

@SuppressWarnings("deprecation")
public class YcjwUtil {

	public static boolean isRightInfo(String Txt_UserName, String Txt_Password) {
		@SuppressWarnings({ "resource" })
		HttpClient httpclient = new DefaultHttpClient();

		String viewState = "dDwtMTU2MDM2OTk5Nzt0PDtsPGk8MT47PjtsPHQ8O2w8aTwzPjtpPDEzPjs+"
				+ "O2w8dDw7bDxpPDE+O2k8Mz47aTw1PjtpPDc+"
				+ "O2k8OT47aTwxMT47aTwxMz47aTwxNT47aTwxNz47PjtsPHQ8cDxwPGw8QmFja0ltYWdlVXJsOz47bDxodHRwOi8vd3d3Lnljancuemp1dC5lZHUuY24vL2ltYWdlcy9iZy5naWY7Pj47Pjs7Pjt0PHA8cDxsPEJhY2tJbWFnZVVybDs+O2w8aHR0cDovL3d3dy55Y2p3LnpqdXQuZWR1LmNuLy9pbWFnZXMvYmcxLmdpZjs+"
				+ "Pjs+Ozs+O3Q8cDxwPGw8QmFja0ltYWdlVXJsOz47bDxodHRwOi8vd3d3Lnljancuemp1dC5lZHUuY24vL2ltYWdlcy9iZzEuZ2lmOz4+"
				+ "Oz47Oz47dDxwPHA8bDxCYWNrSW1hZ2VVcmw7PjtsPGh0dHA6Ly93d3cueWNqdy56anV0LmVkdS5jbi8vaW1hZ2VzL2JnMS5naWY7Pj47Pjs7Pjt0PHA8cDxsPEJhY2tJbWFnZVVybDs+"
				+ "O2w8aHR0cDovL3d3dy55Y2p3LnpqdXQuZWR1LmNuLy9pbWFnZXMvYmcxLmdpZjs+Pjs+Ozs+"
				+ "O3Q8cDxwPGw8QmFja0ltYWdlVXJsOz47bDxodHRwOi8vd3d3Lnljancuemp1dC5lZHUuY24vL2ltYWdlcy9iZzEuZ2lmOz4+"
				+ "Oz47Oz47dDxwPHA8bDxCYWNrSW1hZ2VVcmw7PjtsPGh0dHA6Ly93d3cueWNqdy56anV0LmVkdS5jbi8vaW1hZ2VzL2JnMS5naWY7Pj47Pjs7Pjt0PHA8cDxsPEJhY2tJbWFnZVVybDs+"
				+ "O2w8aHR0cDovL3d3dy55Y2p3LnpqdXQuZWR1LmNuLy9pbWFnZXMvYmcxLmdpZjs+Pjs+Ozs+"
				+ "O3Q8cDxwPGw8QmFja0ltYWdlVXJsOz47bDxodHRwOi8vd3d3Lnljancuemp1dC5lZHUuY24vL2ltYWdlcy9iZzEuZ2lmOz4+Oz47Oz47Pj47dDx0PDt0PGk8Mz47QDwtLeeUqOaIt+"
				+ "exu+Weiy0tO+aVmeW4iDvlrabnlJ87PjtAPC0t55So5oi357G75Z6LLS075pWZ5biIO+WtpueUnzs+Pjs+Ozs+Oz4+Oz4+O2w8SW1nX0RMOz4+qmizg8nuU1ebhUFzNA/qu71sECk=";

		HttpPost httpost = new HttpPost(
				"http://www.ycjw.zjut.edu.cn/logon.aspx");
		/* 填充需要提交的表单 */
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("__VIEWSTATE", viewState));
		nvps.add(new BasicNameValuePair("Txt_UserName", Txt_UserName));
		nvps.add(new BasicNameValuePair("Txt_Password", Txt_Password));
		nvps.add(new BasicNameValuePair("Img_DL.x", "32"));
		nvps.add(new BasicNameValuePair("Img_DL.y", "11"));
		nvps.add(new BasicNameValuePair("Cbo_LX", "学生"));
		nvps.add(new BasicNameValuePair("__EVENTTARGET", ""));
		nvps.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
		// nvps.add(new BasicNameValuePair("Button1", ""));
		/* 设置字符 */
		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
		/* 尝试登陆 */
		HttpResponse response;
		try {
			response = httpclient.execute(httpost);
			/* 判断是否登陆成功，根据登陆成功后会返回302跳转 */
			String result = response.getStatusLine().toString();
			if (result.equals("HTTP/1.1 302 Found")) {
				return true;
			} else {
				return false;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	// test
	public static void main(String[] args) {

		// String result = httpRequest(url.replace("{viewstate}", viewState));
		// System.out.println(result);
		// getCLssTable("ASP.NET_SessionId=xlecmo45k0grf4fugxoyfa45");
		List<StudentTable> list = queryTableList("学号", "密码",
				"classLis", "2015/2016(2)");
		for (int i = 0; i < list.size(); i++) {
			list.get(i);
			System.out.println(list.get(i));
		}
	}

	@SuppressWarnings({ "resource" })
	public static String queryTableUtil(String Txt_UserName,
			String Txt_Password, String queryMethod, String xueqi) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(
				"http://www.ycjw.zjut.edu.cn/logon.aspx");

		String viewStateForLogin = "dDwtMTU2MDM2OTk5Nzt0PDtsPGk8MT47PjtsPHQ8O2w8aTwzPjtpPDEzPjs+"
				+ "O2w8dDw7bDxpPDE+O2k8Mz47aTw1PjtpPDc+"
				+ "O2k8OT47aTwxMT47aTwxMz47aTwxNT47aTwxNz47PjtsPHQ8cDxwPGw8QmFja0ltYWdlVXJsOz47bDxodHRwOi8vd3d3Lnljancuemp1dC5lZHUuY24vL2ltYWdlcy9iZy5naWY7Pj47Pjs7Pjt0PHA8cDxsPEJhY2tJbWFnZVVybDs+O2w8aHR0cDovL3d3dy55Y2p3LnpqdXQuZWR1LmNuLy9pbWFnZXMvYmcxLmdpZjs+"
				+ "Pjs+Ozs+O3Q8cDxwPGw8QmFja0ltYWdlVXJsOz47bDxodHRwOi8vd3d3Lnljancuemp1dC5lZHUuY24vL2ltYWdlcy9iZzEuZ2lmOz4+"
				+ "Oz47Oz47dDxwPHA8bDxCYWNrSW1hZ2VVcmw7PjtsPGh0dHA6Ly93d3cueWNqdy56anV0LmVkdS5jbi8vaW1hZ2VzL2JnMS5naWY7Pj47Pjs7Pjt0PHA8cDxsPEJhY2tJbWFnZVVybDs+"
				+ "O2w8aHR0cDovL3d3dy55Y2p3LnpqdXQuZWR1LmNuLy9pbWFnZXMvYmcxLmdpZjs+Pjs+Ozs+"
				+ "O3Q8cDxwPGw8QmFja0ltYWdlVXJsOz47bDxodHRwOi8vd3d3Lnljancuemp1dC5lZHUuY24vL2ltYWdlcy9iZzEuZ2lmOz4+"
				+ "Oz47Oz47dDxwPHA8bDxCYWNrSW1hZ2VVcmw7PjtsPGh0dHA6Ly93d3cueWNqdy56anV0LmVkdS5jbi8vaW1hZ2VzL2JnMS5naWY7Pj47Pjs7Pjt0PHA8cDxsPEJhY2tJbWFnZVVybDs+"
				+ "O2w8aHR0cDovL3d3dy55Y2p3LnpqdXQuZWR1LmNuLy9pbWFnZXMvYmcxLmdpZjs+Pjs+Ozs+"
				+ "O3Q8cDxwPGw8QmFja0ltYWdlVXJsOz47bDxodHRwOi8vd3d3Lnljancuemp1dC5lZHUuY24vL2ltYWdlcy9iZzEuZ2lmOz4+Oz47Oz47Pj47dDx0PDt0PGk8Mz47QDwtLeeUqOaIt+"
				+ "exu+Weiy0tO+aVmeW4iDvlrabnlJ87PjtAPC0t55So5oi357G75Z6LLS075pWZ5biIO+WtpueUnzs+Pjs+Ozs+Oz4+Oz4+O2w8SW1nX0RMOz4+qmizg8nuU1ebhUFzNA/qu71sECk=";

		/* 填充需要提交的表单 */
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("__VIEWSTATE", viewStateForLogin));
		nvps.add(new BasicNameValuePair("Txt_UserName", Txt_UserName));
		nvps.add(new BasicNameValuePair("Txt_Password", Txt_Password));
		nvps.add(new BasicNameValuePair("Img_DL.x", "32"));
		nvps.add(new BasicNameValuePair("Img_DL.y", "11"));
		nvps.add(new BasicNameValuePair("Cbo_LX", "学生"));
		nvps.add(new BasicNameValuePair("__EVENTTARGET", ""));
		nvps.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
		// nvps.add(new BasicNameValuePair("Button1", ""));
		/* 设置字符 */
		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
		/* 尝试登陆 */
		HttpResponse response;
		try {
			response = httpclient.execute(httpost);
			/* 判断是否登陆成功，根据登陆成功后会返回302跳转 */
			String result = response.getStatusLine().toString();
			if (result.equals("HTTP/1.1 302 Found")) {
				// String set_cookie =
				// response.getFirstHeader("Set-Cookie").getValue();
				String __VIEWSTATE = "dDw5NjExNjI1OTE7dDw7bDxpPDE+Oz47bDx0PDtsPGk8NT47aTw3PjtpPDk+O2k8MTU+O2k8MTk+O2k8MjE+Oz47bDx0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w85a2m55Sf566h55CGXD7mn6Xor6Lns7vnu59cPuWtpueUn+ivvuihqOafpeivojs+Pjs+Ozs+Oz4+O3Q8cDxwPGw8VGV4dDs+O2w8MjAxNi0wNi0xNSAwNjowMDoxNzs+Pjs+Ozs+O3Q8dDw7dDxpPDQxPjtAPDE5OTkvMjAwMCgxKTsxOTk5LzIwMDAoMik7MjAwMC8yMDAxKDEpOzIwMDAvMjAwMSgyKTsyMDAxLzIwMDIoMSk7MjAwMS8yMDAyKDIpOzIwMDIvMjAwMygxKTsyMDAyLzIwMDMoMik7MjAwMy8yMDA0KDEpOzIwMDMvMjAwNCgyKTsyMDA0LzIwMDUoMSk7MjAwNC8yMDA1KDIpOzIwMDUvMjAwNigxKTsyMDA1LzIwMDYoMik7MjAwNi8yMDA3KDEpOzIwMDYvMjAwNygyKTsyMDA3LzIwMDgoMSk7MjAwNy8yMDA4KDIpOzIwMDgvMjAwOSgxKTsyMDA4LzIwMDkoMik7MjAwOS8yMDEwKDEpOzIwMDkvMjAxMCgyKTsyMDEwLzIwMTEoMSk7MjAxMC8yMDExKDIpOzIwMTEvMjAxMigxKTsyMDExLzIwMTIoMik7MjAxMi8yMDEzKDEpOzIwMTIvMjAxMygyKTsyMDEzLzIwMTQoMSk7MjAxMy8yMDE0KDIpOzIwMTQvMjAxNSgxKTsyMDE0LzIwMTUoMik7MjAxNS8yMDE2KDEpOzIwMTUvMjAxNigyKTsyMDE2LzIwMTcoMSk7MjAxNi8yMDE3KDIpOzIwMTcvMjAxOCgxKTsyMDE3LzIwMTgoMik7MjAxOC8yMDE5KDEpOzIwMTgvMjAxOSgyKTsyMDk4LzIwOTkoMSk7PjtAPDE5OTkvMjAwMCgxKTsxOTk5LzIwMDAoMik7MjAwMC8yMDAxKDEpOzIwMDAvMjAwMSgyKTsyMDAxLzIwMDIoMSk7MjAwMS8yMDAyKDIpOzIwMDIvMjAwMygxKTsyMDAyLzIwMDMoMik7MjAwMy8yMDA0KDEpOzIwMDMvMjAwNCgyKTsyMDA0LzIwMDUoMSk7MjAwNC8yMDA1KDIpOzIwMDUvMjAwNigxKTsyMDA1LzIwMDYoMik7MjAwNi8yMDA3KDEpOzIwMDYvMjAwNygyKTsyMDA3LzIwMDgoMSk7MjAwNy8yMDA4KDIpOzIwMDgvMjAwOSgxKTsyMDA4LzIwMDkoMik7MjAwOS8yMDEwKDEpOzIwMDkvMjAxMCgyKTsyMDEwLzIwMTEoMSk7MjAxMC8yMDExKDIpOzIwMTEvMjAxMigxKTsyMDExLzIwMTIoMik7MjAxMi8yMDEzKDEpOzIwMTIvMjAxMygyKTsyMDEzLzIwMTQoMSk7MjAxMy8yMDE0KDIpOzIwMTQvMjAxNSgxKTsyMDE0LzIwMTUoMik7MjAxNS8yMDE2KDEpOzIwMTUvMjAxNigyKTsyMDE2LzIwMTcoMSk7MjAxNi8yMDE3KDIpOzIwMTcvMjAxOCgxKTsyMDE3LzIwMTgoMik7MjAxOC8yMDE5KDEpOzIwMTgvMjAxOSgyKTsyMDk4LzIwOTkoMSk7Pj47bDxpPDMyPjs+Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOWtpueUnzrnqIvluIbnmoTor77ooajkv6Hmga87Pj47Pjs7Pjt0PEAwPHA8cDxsPFBhZ2VDb3VudDtfIURhdGFTb3VyY2VJdGVtQ291bnQ7XyFJdGVtQ291bnQ7RGF0YUtleXM7PjtsPGk8MT47aTwxMj47aTwxMj47bDxpPDE0NTU5PjtpPDIyMDk4PjtpPDIwNTEzPjtpPDEzODYxPjtpPDEzMDg0PjtpPDEwNj47aTwxNzM3OD47aTwyNDc0Nj47aTwxNzExNz47aTwyNDE1Nz47aTwyMzI5Nj47aTw2NTk+Oz47Pj47Pjs7Ozs7Ozs7Ozs+O2w8aTwwPjs+O2w8dDw7bDxpPDE+O2k8Mj47aTwzPjtpPDQ+O2k8NT47aTw2PjtpPDc+O2k8OD47aTw5PjtpPDEwPjtpPDExPjtpPDEyPjs+O2w8dDw7bDxpPDA+O2k8MT47aTwyPjtpPDM+O2k8ND47aTw1Pjs+O2w8dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPOaVsOaNrue7k+aehOWkp+Wei+WunumqjOKFoDrpmYjlv5fmnaggIDs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDzorqHnrpfmnLrnp5HlrabkuI7mioDmnK/lrabpmaI7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8MC41Oz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPDHlkag7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8XGU7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w85b+F5L+u6K++Oz4+Oz47Oz47Pj47Pj47dDw7bDxpPDA+O2k8MT47aTwyPjtpPDM+O2k8ND47aTw1Pjs+O2w8dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPOiupOefpeWunuS5oOKFoDrmsZ/pookgIDs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDzorqHnrpfmnLrnp5HlrabkuI7mioDmnK/lrabpmaI7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8MC40Oz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPDAuOOWRqDs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDxcZTs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDzlv4Xkv67or747Pj47Pjs7Pjs+Pjs+Pjt0PDtsPGk8MD47aTwxPjtpPDI+O2k8Mz47aTw0PjtpPDU+Oz47bDx0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w85aSn5a2m55Sf6IGM5Lia5Y+R5bGV5LiO5bCx5Lia5oyH5a+877yI5Lit77yJ4oWgOueOi+eCs+W/oCAgOz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPOWtpueUn+WkhDs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDwwLjU7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8ODs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDxcZTs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDzlhazpgInor747Pj47Pjs7Pjs+Pjs+Pjt0PDtsPGk8MD47aTwxPjtpPDI+O2k8Mz47aTw0PjtpPDU+Oz47bDx0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w85L2T6IKyOueOi+W4uOm+mSAgOz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPOS9k+WGm+mDqDs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDwxOz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPDMyOz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPDEtMTY65pif5pyfMyg2LTcpIDs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDzpmZDpgInor747Pj47Pjs7Pjs+Pjs+Pjt0PDtsPGk8MD47aTwxPjtpPDI+O2k8Mz47aTw0PjtpPDU+Oz47bDx0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w86ams5YWL5oCd5Li75LmJ5Z+65pys5Y6f55CG4oWgOum7hOaysyAgOz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPOmprOWFi+aAneS4u+S5ieWtpumZojs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDwzOz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPDQ4Oz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPDEtMTblkag65pif5pyfMSgxMC0xMikgIOazleWtpkExMDZcOzs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDzlv4Xkv67or747Pj47Pjs7Pjs+Pjs+Pjt0PDtsPGk8MD47aTwxPjtpPDI+O2k8Mz47aTw0PjtpPDU+Oz47bDx0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w85qaC546H6K665LiO5pWw55CG57uf6K6hQuKFoDrlvKDlhqzmooUgIDs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDznkIblrabpmaI7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8Mzs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDw0ODs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDwxLTE25ZGoOuaYn+acnzEoMy00KSAg5LuB5ZKMMTExXDsxLTjlkag65pif5pyfNCgxLTIpICDku4HlkowxMTBcOzs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDzlv4Xkv67or747Pj47Pjs7Pjs+Pjs+Pjt0PDtsPGk8MD47aTwxPjtpPDI+O2k8Mz47aTw0PjtpPDU+Oz47bDx0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w85pWw5o2u57uT5p6E4oWgOumZiOW/l+adqCAgOz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPOiuoeeul+acuuenkeWtpuS4juaKgOacr+WtpumZojs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDw0Oz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPDY0Oz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPDEtMTblkag65pif5pyfMSg2LTcpICDljZrmmJNDNTA1XDsxLTE25ZGoOuaYn+acnzQoNi03KSAg6YOB5paHQjQwOVw7Oz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPOW/heS/ruivvjs+Pjs+Ozs+Oz4+Oz4+O3Q8O2w8aTwwPjtpPDE+O2k8Mj47aTwzPjtpPDQ+O2k8NT47PjtsPHQ8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDznvo7lm73ljoblj7LmlofljJbkuI7lpKflrabihaA65byg5Yek5aifICA7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w85pS/5rK75LiO5YWs5YWx566h55CG5a2m6ZmiOz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPDI7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8MzI7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8MS0xNuWRqDrmmJ/mnJ8xKDgtOSkgIOW5v0IxMTNcOzs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDzlhazpgInor747Pj47Pjs7Pjs+Pjs+Pjt0PDtsPGk8MD47aTwxPjtpPDI+O2k8Mz47aTw0PjtpPDU+Oz47bDx0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8SmF2Yeeoi+W6j+iuvuiuoeKFoDrnm5vkvJ/lm70gIDs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDzorqHnrpfmnLrnp5HlrabkuI7mioDmnK/lrabpmaI7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8Mzs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDw0ODs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDwxLTE25ZGoOuaYn+acnzIoNi03KSAg5bm/QjExMlw7MS045ZGoOuaYn+acnzQoMy00KSAg5LuB5ZKMMjA5XDs7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w85b+F5L+u6K++Oz4+Oz47Oz47Pj47Pj47dDw7bDxpPDA+O2k8MT47aTwyPjtpPDM+O2k8ND47aTw1Pjs+O2w8dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPOWco+e7j+aVheS6i+mAieivu++8iOaPkOmrmOivvu+8ieKFoDrlp5rkvbMgIDs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDzlpJblm73or63lrabpmaI7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8Mjs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDwzMjs+Pjs+Ozs+Oz4+O3Q8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDwxLTE25ZGoOuaYn+acnzMoMS0yKSAg5Y2a5piTQzEwM1w7Oz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPOWFrOmAieivvjs+Pjs+Ozs+Oz4+Oz4+O3Q8O2w8aTwwPjtpPDE+O2k8Mj47aTwzPjtpPDQ+O2k8NT47PjtsPHQ8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDzlvbHop4bkuI7njrDku6PmlofljJbihaA65YyF54eVICA7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w85Lq65paH5a2m6ZmiOz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPDI7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8MzI7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8MS0xNuWRqDrmmJ/mnJ80KDEwLTExKSAg5bm/QjEwM1w7Oz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPOWFrOmAieivvjs+Pjs+Ozs+Oz4+Oz4+O3Q8O2w8aTwwPjtpPDE+O2k8Mj47aTwzPjtpPDQ+O2k8NT47PjtsPHQ8O2w8aTwxPjs+O2w8dDxwPHA8bDxUZXh0Oz47bDzorqHnrpfmnLrnvZHnu5zljp/nkIbihaA66YOt5rC46ImzICA7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w86K6h566X5py656eR5a2m5LiO5oqA5pyv5a2m6ZmiOz4+Oz47Oz47Pj47dDw7bDxpPDE+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPDM7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8NDg7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8OS0xNuWRqDrmmJ/mnJ80KDMtNCkgIOWNmuaYk0MyMDNcOzEtNOWRqDrmmJ/mnJ81KDMtNCkgIOmDgeaWh0IyMTBcOzUtMTblkag65pif5pyfNSgzLTQpICDlub9CMjA2XDs7Pj47Pjs7Pjs+Pjt0PDtsPGk8MT47PjtsPHQ8cDxwPGw8VGV4dDs+O2w85b+F5L+u6K++Oz4+Oz47Oz47Pj47Pj47Pj47Pj47dDxAMDxwPHA8bDxQYWdlQ291bnQ7XyFJdGVtQ291bnQ7XyFEYXRhU291cmNlSXRlbUNvdW50O0RhdGFLZXlzOz47bDxpPDA+O2k8LTE+O2k8LTE+O2w8Pjs+Pjs+Ozs7Ozs7Ozs7Oz47Oz47Pj47Pj47Prs/ssOPBznMWxG6nisAf111BSpe";
				HttpEntity entity = response.getEntity();
				// System.out.println(response.toString());
				EntityUtils.consume(entity);
				// 访问查看课表链接
				HttpPost httpostForTable = new HttpPost(
						"http://www.ycjw.zjut.edu.cn//stdgl/cxxt/Web_Std_XQKB.aspx");

				/* 填充需要提交的表单 */
				List<NameValuePair> nvpsForTable = new ArrayList<NameValuePair>();
				nvpsForTable.add(new BasicNameValuePair("__VIEWSTATE",
						__VIEWSTATE));
				nvpsForTable.add(new BasicNameValuePair("__EVENTTARGET", ""));
				nvpsForTable.add(new BasicNameValuePair("Cbo_Xueqi", xueqi));
				nvpsForTable.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
				if (queryMethod.equals("按课程查询")) {
					nvpsForTable.add(new BasicNameValuePair("Button1", ""));
				} else {
					nvpsForTable.add(new BasicNameValuePair("Button2", ""));
				}
				/* 设置字符 */
				httpostForTable.setEntity(new UrlEncodedFormEntity(
						nvpsForTable, Consts.UTF_8));
				response = httpclient.execute(httpostForTable);
				HttpEntity entity1 = response.getEntity();
				// 获取课表信息
				String table = null;
				if (queryMethod.equals("按课程查询")) {
					table = extractForTableList(dump(entity1));
					EntityUtils.consume(entity1);
					return table;
				} else {
					// 得到的是html代码
					table = extractForTable(dump(entity1));
					EntityUtils.consume(entity1);
					return table;
				}

			} else {
				System.out.println("qw");
				return null;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询课表
	 * @param Txt_UserName
	 * @param Txt_Password
	 * @param queryMethod
	 * @param xueqi
	 * @return
	 */
	public static List<String> queryTable(String Txt_UserName,
			String Txt_Password, String queryMethod, String xueqi) {

		String table = queryTableUtil(Txt_UserName, Txt_Password, queryMethod,
				xueqi);
		List<String> list = new ArrayList<>();
		list.add(table);

		return list;
	}

	/**
	 * 查询课程列表
	 * @param Txt_UserName
	 * @param Txt_Password
	 * @param queryMethod
	 * @param xueqi
	 * @return
	 */
	public static List<StudentTable> queryTableList(String Txt_UserName,
			String Txt_Password, String queryMethod, String xueqi) {

		String table = queryTableUtil(Txt_UserName, Txt_Password, queryMethod,
				xueqi);

		// 处理得到而数据
		List<StudentTable> list = new ArrayList<>();
		String[] _table = table.split("\n");
		int flag = 0;
		StudentTable st = null;
		for (int i = 0; i < _table.length; i++) {
			switch (flag) {
			case 0:
				st = new StudentTable();
				st.setClassName(_table[i]);
				flag++;
				break;
			case 1:
				st.setClassCollege(_table[i]);
				flag++;
				break;
			case 2:
				st.setCredit(_table[i]);
				flag++;
				break;
			case 3:
				st.setStudyHour(_table[i]);
				flag++;
				break;
			case 4:
				st.setLocation(_table[i]);
				flag++;
				break;
			case 5:
				st.setClassStyle(_table[i]);
				list.add(st);
				flag = 0;
				break;

			default:
				break;
			}
		}
		return list;
	}

	/**
	 * 读入网页源代码
	 * 
	 * @param entity
	 * @return
	 * @throws IOException
	 */
	private static String dump(HttpEntity entity) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				entity.getContent(), "GBK"));
		String line;
		String result = null;
		while (null != (line = br.readLine())) {
			result += line;
		}
		return result;
	}

	/**
	 * 查询课程列表 筛选html 保留有效数据
	 * 
	 * @param html
	 * @return
	 */
	private static String extractForTableList(String html) {
		StringBuffer buffer = null;
		StringBuffer result = new StringBuffer();
		// table标签 获得课表数据
		Pattern p = Pattern
				.compile("(.*)(<table cellspacing=\"0\" cellpadding=\"0\" rules=\"all\" bordercolor=\"#7691C7\" border=\"1\" id=\"DG_PTHasselect\" bgcolor=\"White\" height=\"30\" width=\"100%\">)(.*?)(</table>)(.*)");
		Matcher m = p.matcher(html);

		if (m.matches()) {
			buffer = new StringBuffer();

			// 将html代码移除
			for (String info : m.group(3).split("  ")) {
				info.replaceAll("</?[^>]+>", "").trim();
				// 将有效数据添加给buffer
				if (!"".equals(info)) {
					buffer.append(info);// .append("\n\n");
				}
			}
		}
		// 抽取需要的数据
		// String s =
		// "<span id=\"gfds\">发光飞碟广东省</span>tretret<spanid=\"gfd\" >rewerwq</span>";
		// <td rowspan="2"><font size="2"><span
		// id="DG_GXK__ctl11_XQ5">计算机组成原理Ⅰ/(1-16周)|博易楼/博易B207/范玉雷</span></font></td>
		Pattern pa = Pattern.compile("<span[^>]*>(.*?)</span>");
		Matcher ma = pa.matcher(buffer);
		while (ma.find()) {

			// String temp = ma.group(1);
			result.append(ma.group(1)).append("\n");

		}
		return result.toString();
	}

	/**
	 * 按课表查询
	 * 
	 * @param html
	 * @return
	 */
	private static String extractForTable(String html) {
		StringBuffer buffer = null;
		// <td rowspan="2"><font size="2"><span
		// <table class="style6" cellspacing="0" cellpadding="1" rules="all"
		// bordercolor="#2650A6" border="2" id="DG_GXK" bgcolor="#F1F3F9"
		// height="100%" width="100%">
		// table标签 获得课表数据
		Pattern p = Pattern
				.compile("(.*)(<table class=\"style6\" cellspacing=\"0\" cellpadding=\"1\" rules=\"all\" bordercolor=\"#2650A6\" border=\"2\" id=\"DG_GXK\" bgcolor=\"#F1F3F9\" height=\"100%\" width=\"100%\">)(.*?)(</table>)(.*)");

		Matcher m = p.matcher(html);
		if (m.matches()) {
			buffer = new StringBuffer();
			// 将html代码移除
			for (String info : m.group(3).split("  ")) {
				// info.replaceAll("</?[^>]+>", "").trim();
				// 将有效数据添加给buffer
				// System.out.println(m.group(3));
				if (!"".equals(info)) {
					buffer.append(info);
				}
			}
		}

		return buffer.toString();
	}

	
	
	/**
	 * 查询成绩的util
	 * @param Txt_UserName
	 * @param Txt_Password
	 * @param xueqi
	 * @return 成绩单html界面
	 */
	@SuppressWarnings({ "resource" })
	public static String queryScoreUtil(String Txt_UserName,
			String Txt_Password, String xueqi) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(
				"http://www.ycjw.zjut.edu.cn/logon.aspx");

		String viewStateForLogin = "dDwtMTU2MDM2OTk5Nzt0PDtsPGk8MT47PjtsPHQ8O2w8aTwzPjtpPDEzPjs+"
				+ "O2w8dDw7bDxpPDE+O2k8Mz47aTw1PjtpPDc+"
				+ "O2k8OT47aTwxMT47aTwxMz47aTwxNT47aTwxNz47PjtsPHQ8cDxwPGw8QmFja0ltYWdlVXJsOz47bDxodHRwOi8vd3d3Lnljancuemp1dC5lZHUuY24vL2ltYWdlcy9iZy5naWY7Pj47Pjs7Pjt0PHA8cDxsPEJhY2tJbWFnZVVybDs+O2w8aHR0cDovL3d3dy55Y2p3LnpqdXQuZWR1LmNuLy9pbWFnZXMvYmcxLmdpZjs+"
				+ "Pjs+Ozs+O3Q8cDxwPGw8QmFja0ltYWdlVXJsOz47bDxodHRwOi8vd3d3Lnljancuemp1dC5lZHUuY24vL2ltYWdlcy9iZzEuZ2lmOz4+"
				+ "Oz47Oz47dDxwPHA8bDxCYWNrSW1hZ2VVcmw7PjtsPGh0dHA6Ly93d3cueWNqdy56anV0LmVkdS5jbi8vaW1hZ2VzL2JnMS5naWY7Pj47Pjs7Pjt0PHA8cDxsPEJhY2tJbWFnZVVybDs+"
				+ "O2w8aHR0cDovL3d3dy55Y2p3LnpqdXQuZWR1LmNuLy9pbWFnZXMvYmcxLmdpZjs+Pjs+Ozs+"
				+ "O3Q8cDxwPGw8QmFja0ltYWdlVXJsOz47bDxodHRwOi8vd3d3Lnljancuemp1dC5lZHUuY24vL2ltYWdlcy9iZzEuZ2lmOz4+"
				+ "Oz47Oz47dDxwPHA8bDxCYWNrSW1hZ2VVcmw7PjtsPGh0dHA6Ly93d3cueWNqdy56anV0LmVkdS5jbi8vaW1hZ2VzL2JnMS5naWY7Pj47Pjs7Pjt0PHA8cDxsPEJhY2tJbWFnZVVybDs+"
				+ "O2w8aHR0cDovL3d3dy55Y2p3LnpqdXQuZWR1LmNuLy9pbWFnZXMvYmcxLmdpZjs+Pjs+Ozs+"
				+ "O3Q8cDxwPGw8QmFja0ltYWdlVXJsOz47bDxodHRwOi8vd3d3Lnljancuemp1dC5lZHUuY24vL2ltYWdlcy9iZzEuZ2lmOz4+Oz47Oz47Pj47dDx0PDt0PGk8Mz47QDwtLeeUqOaIt+"
				+ "exu+Weiy0tO+aVmeW4iDvlrabnlJ87PjtAPC0t55So5oi357G75Z6LLS075pWZ5biIO+WtpueUnzs+Pjs+Ozs+Oz4+Oz4+O2w8SW1nX0RMOz4+qmizg8nuU1ebhUFzNA/qu71sECk=";

		/* 填充需要提交的表单 */
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("__VIEWSTATE", viewStateForLogin));
		nvps.add(new BasicNameValuePair("Txt_UserName", Txt_UserName));
		nvps.add(new BasicNameValuePair("Txt_Password", Txt_Password));
		nvps.add(new BasicNameValuePair("Img_DL.x", "32"));
		nvps.add(new BasicNameValuePair("Img_DL.y", "11"));
		nvps.add(new BasicNameValuePair("Cbo_LX", "学生"));
		nvps.add(new BasicNameValuePair("__EVENTTARGET", ""));
		nvps.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
		/* 设置字符 */
		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
		/* 尝试登陆 */
		HttpResponse response;
		try {
			response = httpclient.execute(httpost);
			/* 判断是否登陆成功，根据登陆成功后会返回302跳转 */
			String result = response.getStatusLine().toString();
			if (result.equals("HTTP/1.1 302 Found")) {
				// String set_cookie =
				// response.getFirstHeader("Set-Cookie").getValue();
				String __VIEWSTATE = "dDw4Mjc4OTE1MTQ7dDw7bDxpPDE+Oz47bDx0PDtsPGk8NT47aTw3PjtpPDk+O2k8MTM+O2k8MTc+O2k8MjE+O2k8MzU+Oz47bDx0PHA8cDxsPFRleHQ7PjtsPOWtpuWPt++8mjIwMTQyNjgxMDMwMzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85aeT5ZCN77ya56iL5biGOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDznj63nuqfvvJrova/ku7blt6XnqIsxNDAzOz4+Oz47Oz47dDx0PDt0PGk8NDM+O0A8XGU777yd5omA5pyJ5a2m5pyf77ydOzE5OTkvMjAwMCgxKTsxOTk5LzIwMDAoMik7MjAwMC8yMDAxKDEpOzIwMDAvMjAwMSgyKTsyMDAxLzIwMDIoMSk7MjAwMS8yMDAyKDIpOzIwMDIvMjAwMygxKTsyMDAyLzIwMDMoMik7MjAwMy8yMDA0KDEpOzIwMDMvMjAwNCgyKTsyMDA0LzIwMDUoMSk7MjAwNC8yMDA1KDIpOzIwMDUvMjAwNigxKTsyMDA1LzIwMDYoMik7MjAwNi8yMDA3KDEpOzIwMDYvMjAwNygyKTsyMDA3LzIwMDgoMSk7MjAwNy8yMDA4KDIpOzIwMDgvMjAwOSgxKTsyMDA4LzIwMDkoMik7MjAwOS8yMDEwKDEpOzIwMDkvMjAxMCgyKTsyMDEwLzIwMTEoMSk7MjAxMC8yMDExKDIpOzIwMTEvMjAxMigxKTsyMDExLzIwMTIoMik7MjAxMi8yMDEzKDEpOzIwMTIvMjAxMygyKTsyMDk4LzIwOTkoMSk7MjAxMy8yMDE0KDEpOzIwMTMvMjAxNCgyKTsyMDE0LzIwMTUoMSk7MjAxNC8yMDE1KDIpOzIwMTUvMjAxNigxKTsyMDE1LzIwMTYoMik7MjAxNi8yMDE3KDEpOzIwMTYvMjAxNygyKTsyMDE3LzIwMTgoMSk7MjAxNy8yMDE4KDIpOzIwMTgvMjAxOSgxKTsyMDE4LzIwMTkoMik7PjtAPFxlO++8neaJgOacieWtpuacn++8nTsxOTk5LzIwMDAoMSk7MTk5OS8yMDAwKDIpOzIwMDAvMjAwMSgxKTsyMDAwLzIwMDEoMik7MjAwMS8yMDAyKDEpOzIwMDEvMjAwMigyKTsyMDAyLzIwMDMoMSk7MjAwMi8yMDAzKDIpOzIwMDMvMjAwNCgxKTsyMDAzLzIwMDQoMik7MjAwNC8yMDA1KDEpOzIwMDQvMjAwNSgyKTsyMDA1LzIwMDYoMSk7MjAwNS8yMDA2KDIpOzIwMDYvMjAwNygxKTsyMDA2LzIwMDcoMik7MjAwNy8yMDA4KDEpOzIwMDcvMjAwOCgyKTsyMDA4LzIwMDkoMSk7MjAwOC8yMDA5KDIpOzIwMDkvMjAxMCgxKTsyMDA5LzIwMTAoMik7MjAxMC8yMDExKDEpOzIwMTAvMjAxMSgyKTsyMDExLzIwMTIoMSk7MjAxMS8yMDEyKDIpOzIwMTIvMjAxMygxKTsyMDEyLzIwMTMoMik7MjA5OC8yMDk5KDEpOzIwMTMvMjAxNCgxKTsyMDEzLzIwMTQoMik7MjAxNC8yMDE1KDEpOzIwMTQvMjAxNSgyKTsyMDE1LzIwMTYoMSk7MjAxNS8yMDE2KDIpOzIwMTYvMjAxNygxKTsyMDE2LzIwMTcoMik7MjAxNy8yMDE4KDEpOzIwMTcvMjAxOCgyKTsyMDE4LzIwMTkoMSk7MjAxOC8yMDE5KDIpOz4+O2w8aTwzNj47Pj47Oz47dDx0PDt0PGk8Mjk+O0A8XGU777yd5omA5pyJ5a2m5bm077ydOzE5OTkvMjAwMDsyMDAwLzIwMDE7MjAwMS8yMDAyOzIwMDIvMjAwMzsyMDAzLzIwMDQ7MjAwNC8yMDA1OzIwMDUvMjAwNjsyMDA2LzIwMDc7MjAwNy8yMDA4OzIwMDgvMjAwOTsyMDA5LzIwMTA7MjAxMC8yMDExOzIwMTEvMjAxMjsyMDEyLzIwMTM7MjAxMy8yMDE0OzIwMTQvMjAxNTsyMDE1LzIwMTY7MjAxNi8yMDE3OzIwMTcvMjAxODsyMDE4LzIwMTk7MjAxOS8yMDIwOzIwMjAvMjAyMTsyMDIxLzIwMjI7MjAyMi8yMDIzOzIwMjMvMjAyNDsyMDI0LzIwMjU7MjA5OC8yMDk5Oz47QDxcZTvvvJ3miYDmnInlrablubTvvJ07MTk5OS8yMDAwOzIwMDAvMjAwMTsyMDAxLzIwMDI7MjAwMi8yMDAzOzIwMDMvMjAwNDsyMDA0LzIwMDU7MjAwNS8yMDA2OzIwMDYvMjAwNzsyMDA3LzIwMDg7MjAwOC8yMDA5OzIwMDkvMjAxMDsyMDEwLzIwMTE7MjAxMS8yMDEyOzIwMTIvMjAxMzsyMDEzLzIwMTQ7MjAxNC8yMDE1OzIwMTUvMjAxNjsyMDE2LzIwMTc7MjAxNy8yMDE4OzIwMTgvMjAxOTsyMDE5LzIwMjA7MjAyMC8yMDIxOzIwMjEvMjAyMjsyMDIyLzIwMjM7MjAyMy8yMDI0OzIwMjQvMjAyNTsyMDk4LzIwOTk7Pj47Pjs7Pjt0PHQ8O3Q8aTwyPjtAPO+8neaJgOacieivvueoi++8nTvkvZPogrLihaM7PjtAPO+8neaJgOacieivvueoi++8nTvkvZPogrLihaM7Pj47Pjs7Pjt0PEAwPDs7Ozs7Ozs7Ozs+Ozs+Oz4+Oz4+O2w8cmJ0blhxO3JidG5YbjtyYnRuWG47Pj5Ye3gR6xL6O2+XTUkZtedt2wHs4Q==";
				HttpEntity entity = response.getEntity();
				// System.out.println(response.toString());
				EntityUtils.consume(entity);
				// 访问查看课表链接
				HttpPost httpostForTable = new HttpPost(
						"http://www.ycjw.zjut.edu.cn//stdgl/cxxt/cjcx/Cjcx_Xsgrcj.aspx");

				/* 填充需要提交的表单 */
				List<NameValuePair> nvpsForTable = new ArrayList<NameValuePair>();
				nvpsForTable.add(new BasicNameValuePair("__VIEWSTATE",
						__VIEWSTATE));
				nvpsForTable.add(new BasicNameValuePair("__EVENTTARGET", ""));
				nvpsForTable.add(new BasicNameValuePair("ddlXq", xueqi));
				nvpsForTable.add(new BasicNameValuePair("ddlkc", ""));
				nvpsForTable.add(new BasicNameValuePair("1", "rbtnXq"));
				nvpsForTable.add(new BasicNameValuePair("__EVENTARGUMENT", ""));
				
				nvpsForTable.add(new BasicNameValuePair("Button1", ""));
				
				/* 设置字符 */
				httpostForTable.setEntity(new UrlEncodedFormEntity(
						nvpsForTable, Consts.UTF_8));
				response = httpclient.execute(httpostForTable);
				

//				nvpsForTable.add(new BasicNameValuePair("ddlXq", xueqi));
//				httpostForTable.setEntity(new UrlEncodedFormEntity(
//						nvpsForTable, Consts.UTF_8));
//				response = httpclient.execute(httpostForTable);
				
				HttpEntity entity1 = response.getEntity();
				// 获取课表信息
				String score = null;

				if (response.getStatusLine().toString().equals("HTTP/1.1 200 OK")){
					score = extractForScore(dump(entity1));
					EntityUtils.consume(entity1);
					System.out.println("!!!!!!!!!"+score);
					return score;
				}
				return "原创又在玩什么新花样。。。";


			} else {
				System.out.println("qw");
				return null;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("1111111");
		return null;
	}
	
	private static String extractForScore(String html) {
		StringBuffer buffer = null;
		// table标签 获得成绩数据
		Pattern p = Pattern
				.compile("(.*)(<table cellspacing=\"0\" cellpadding=\"3\" rules=\"all\" bordercolor=\"#CCCCCC\" border=\"1\" id=\"DataGrid1\" bgcolor=\"White\" height=\"96\" width=\"539\">)(.*?)(</TABLE>)(.*)");
		Matcher m = p.matcher(html);
		if (m.matches()) {
			buffer = new StringBuffer();
			for (String info : m.group(3).split("  ")) {				
				// 将有效数据添加给buffer
				if (!"".equals(info)) {
					buffer.append(info);
				}
			}
		}
		
		return buffer.toString();
	}
	
	@Test
	public void test() {
		System.out.println(queryScoreUtil("学号", "密码", "2015/1016(2)"));
	}
}
