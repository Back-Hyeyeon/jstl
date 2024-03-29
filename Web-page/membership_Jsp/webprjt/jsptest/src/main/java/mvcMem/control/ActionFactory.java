package mvcMem.control;

import mvcMem.action.Action;
import mvcMem.action.DeleteFormAction;
import mvcMem.action.DeleteProcAction;
import mvcMem.action.IdCheckAction;
import mvcMem.action.IndexAction;
import mvcMem.action.LoginFormAction;
import mvcMem.action.LoginProcAction;
import mvcMem.action.LogoutAction;
import mvcMem.action.ModifyFormAction;
import mvcMem.action.ModifyProcAction;
import mvcMem.action.RegFormAction;
import mvcMem.action.RegProcAction;

//싱글톤 방식 (private)
public class ActionFactory {
	private static ActionFactory factory = new ActionFactory();

	private ActionFactory() {
	}

	public static synchronized ActionFactory getInstance() {
		
		return factory;
	}

	public Action getAction(String cmd) {
		Action action = null;
		switch (cmd) {
		case "index":
			 action = new IndexAction();
			break;
		case "login":
			 action = new LoginFormAction();
			break;
		case "loginProc":
			 action = new LoginProcAction();
			break;
		case "logout":
			 action = new LogoutAction();
			break;
		case "regForm":
			 action = new RegFormAction();
			break;
		case "regProc":
			 action = new RegProcAction();
			break;
		case "modifyForm":
			 action = new ModifyFormAction();
			break;
		case "modifyProc":
			 action = new ModifyProcAction();
			break;
		case "deleteForm":
			 action = new DeleteFormAction();
			break;
		case "deleteProc":
			 action = new DeleteProcAction();
			break;
		case "idCheck":
			 action = new IdCheckAction();
			break;
		case "zipCheck":
			// action = new ZipCheckAction();
			break;
		default:
			// action = new IndexAction();
			break;
		}
		return action;
	}
}
