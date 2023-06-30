package edu.ynu.ecs.security.events;

import edu.ynu.ecs.entities.User;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.core.ResolvableType;

public class LoginSuccessEvent extends PayloadApplicationEvent<User> {

	public LoginSuccessEvent(Object source, User payload) {
		super(source, payload);
	}

	@Override
	public ResolvableType getResolvableType() {
		return ResolvableType.forRawClass(LoginSuccessEvent.class);
	}

	@Override
	public User getPayload() {
		return super.getPayload();
	}

}
