package cn.rabbitmq.send;

public class SendStatusMessage {
	
	private boolean flag;
	
	private String message;

	public SendStatusMessage(boolean flag, String message) {
		this.flag = flag;
		this.message = message;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
