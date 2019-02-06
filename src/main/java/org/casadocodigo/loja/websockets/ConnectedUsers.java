package org.casadocodigo.loja.websockets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.ApplicationScoped;
import javax.websocket.Session;

@ApplicationScoped
public class ConnectedUsers {

	private Set<Session> remoteUsers = new HashSet<>();
	// private Logger logger = LoggerFactory.getLogger(ConnectedUsers.class);

	public boolean add(Session remoteUser) {
		return this.remoteUsers.add(remoteUser);
	}
	
	public void send(String	message) {
		for	(Session user :	remoteUsers) {
			if	(user.isOpen())	{
				try	{
					user.getBasicRemote().sendText(message);
				} catch	(IOException e)	{
					//logger.error("Não	foi	possivel	enviarmensagem	para	um	cliente,	{}",e);
					System.out.println("Não	foi	possivel	enviar mensagem	para	um	cliente,	{}" + e.getMessage());
				}
			} else {
				System.out.println("cliente nao esta mais aberto");
			}
		}
	}
	
	public boolean remove(Session session) {
		return remoteUsers.remove(session);
	}
}
