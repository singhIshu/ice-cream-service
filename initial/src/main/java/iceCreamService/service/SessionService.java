package iceCreamService.service;


import iceCreamService.model.Session;
import iceCreamService.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {

    private SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void addSession(String token, String email) {
        Session session = new Session(token, email);
        sessionRepository.save(session);
    }

    public boolean isValidSession(String token,String email){
        Optional<Session> session = sessionRepository.findById(token);
        if(session.isPresent()){
            return session.get().emailId.equals(email);
        }
        return false;
    }

    public void removeSession(String accessToken) {
        sessionRepository.deleteById(accessToken);
    }
}
