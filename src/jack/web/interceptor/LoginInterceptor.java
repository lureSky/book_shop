package jack.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import jack.domain.User;

public class LoginInterceptor extends MethodFilterInterceptor{

	private static final long serialVersionUID = -4409507846064552966L;
	@Override
	protected String doIntercept(ActionInvocation invoker) throws Exception {
		
		//ȡ��session�е�user����������ھͷ��ص�¼
		User user = (User)ActionContext.getContext().getSession().get("user");
		if(user == null) {
			return "toLogin";
		}
		return invoker.invoke();
	}

}
