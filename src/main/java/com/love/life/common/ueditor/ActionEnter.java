package com.love.life.common.ueditor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class ActionEnter {
	
	private HttpServletRequest request = null;
	
	private String rootPath = null;
	private String contextPath = null;
	
	private String actionType = null;
	
	private ConfigManager configManager = null;

	public ActionEnter ( HttpServletRequest request, String rootPath ) {
	
		this.request = request;
		this.rootPath = rootPath;
		this.actionType = request.getParameter( "action" );
		this.contextPath = request.getContextPath();
		this.configManager = ConfigManager.getInstance( this.rootPath, this.contextPath, request.getRequestURI() );
		
	}
	
	public String exec () {
		
		String callbackName = this.request.getParameter("callback");
		System.out.println(callbackName);
		if ( callbackName != null ) {

			if ( !validCallbackName( callbackName ) ) {
				return new BaseState( false, AppInfo.ILLEGAL ).toJSONString();
			}
			
			return callbackName+"("+this.invoke()+");";
			
		} else {
			return this.invoke();
		}

	}
	
	public String invoke() {
		System.out.println("actionType:"+actionType);
		if ( actionType == null || !ActionMap.mapping.containsKey( actionType ) ) {
			System.out.println(this.getClass()+":1");
			return new BaseState( false, AppInfo.INVALID_ACTION ).toJSONString();
		}
		
		if ( this.configManager == null || !this.configManager.valid() ) {
			System.out.println(this.getClass()+":2");
			return new BaseState( false, AppInfo.CONFIG_ERROR ).toJSONString();
		}
		
		State state = null;
		
		int actionCode = ActionMap.getType( this.actionType );
		System.out.println("actionCode:"+actionCode);
		Map<String, Object> conf = null;
		switch ( actionCode ) {
		
			case ActionMap.CONFIG:
				System.out.println("this.configManager.getAllConfig().toString():"+this.configManager.getAllConfig().toString());
				return this.configManager.getAllConfig().toString();
				
			case ActionMap.UPLOAD_IMAGE:
			case ActionMap.UPLOAD_SCRAWL:
			case ActionMap.UPLOAD_VIDEO:
			case ActionMap.UPLOAD_FILE:
				
				conf = this.configManager.getConfig( actionCode );
				System.out.println("conf:"+conf);
				state = new Uploader( request, conf ).doExec();
				System.out.println("state:"+state);
				break;
				
			case ActionMap.CATCH_IMAGE:
				conf = configManager.getConfig( actionCode );
				System.out.println("conf:"+conf);
				String[] list = this.request.getParameterValues( (String)conf.get( "fieldName" ) );
				state = new ImageHunter( conf ).capture( list );
				System.out.println("state:"+state);
				break;
				
			case ActionMap.LIST_IMAGE:
			case ActionMap.LIST_FILE:
				conf = configManager.getConfig( actionCode );
				System.out.println("conf:"+conf);
				int start = this.getStartIndex();
				System.out.println("start:"+start);
				state = new FileManager( conf ).listFile( start );
				System.out.println("state:"+state);
				break;
				
		}
		System.out.println("state:"+state);
		return state.toJSONString();
		
	}
	
	public int getStartIndex () {
		
		String start = this.request.getParameter( "start" );
		
		try {
			return Integer.parseInt( start );
		} catch ( Exception e ) {
			return 0;
		}
		
	}
	
	/**
	 * callback参数验证
	 */
	public boolean validCallbackName ( String name ) {
		
		if ( name.matches( "^[a-zA-Z_]+[\\w0-9_]*$" ) ) {
			return true;
		}
		
		return false;
		
	}
	
}