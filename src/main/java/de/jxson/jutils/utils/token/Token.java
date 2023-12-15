package de.jxson.jutils.utils.token;

import de.jxson.jutils.entity.session.Session;
import de.jxson.jutils.entity.user.User;
import de.jxson.jutils.repository.session.SessionRepository;

import java.sql.Timestamp;
import java.util.UUID;

public class Token {

    private static final long TOKEN_EXPIRE_MIN = 600000; //10min

    private Token()
    {}

    public static boolean validateToken(Session session, SessionRepository repository)
    {
        if(session.getValidUntil().getTime() < System.currentTimeMillis())
        {
            session.setValid(false);
            repository.saveAndFlush(session);
            return false;
        }
        return true;
    }

    public static Session generateSession(User user, SessionRepository repository)
    {
        Session session = new Session();
        session.setValidUntil(new Timestamp(System.currentTimeMillis()+TOKEN_EXPIRE_MIN));
        session.setToken(UUID.randomUUID().toString().replace("-", ""));
        session.setUser(user);
        session.setValid(true);
        repository.save(session);
        return session;
    }

}
