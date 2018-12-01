package dbutils;

import entities.RacesEntity;
import entities.TicketsEntity;
import entities.UsersEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

public class Validate {

    public static UsersEntity checkUser(String login, String pass) {
        UsersEntity usersEntity = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest.digest(pass.getBytes("UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            for(byte b : bytes){
                stringBuilder.append(b);
            }
            Session session = HibernateUtil.getSession();
            session.getTransaction();
            Query query = session.createQuery("from UsersEntity where userName = :log and userPassword = :pass");
            query.setParameter("log", login);
            query.setParameter("pass", stringBuilder.toString());
            List<UsersEntity> listResult = query.list();
            usersEntity = listResult.isEmpty() ? null : listResult.get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersEntity;
    }

    public static List<RacesEntity> getRaces(String selectedDate){
        List<RacesEntity> listResult = null;
        try {
            Session session = HibernateUtil.getSession();
            session.getTransaction();
            Query query = session.createQuery("from RacesEntity where to_char(raceDate, 'DD.MM.YYYY') = :selectedDate order by raceName ");
            query.setParameter("selectedDate", selectedDate);
            listResult = query.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listResult;
    }

    public static RacesEntity getRace(BigInteger raceId){
        RacesEntity racesEntity = null;
        try {
            Session session = HibernateUtil.getSession();
            session.getTransaction();
            Query query = session.createQuery("from RacesEntity where raceId = :raceId");
            query.setParameter("raceId", raceId);
            racesEntity = (RacesEntity) query.list().get(0);
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return racesEntity;
    }

    public static void saveTicket(TicketsEntity ticketsEntity){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(ticketsEntity);
        if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
            session.getTransaction().commit();
        }
        session.close();
    }

    public static void updateRace(RacesEntity racesEntity){
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            session.update(racesEntity);
            if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
                session.getTransaction().commit();
            }
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TicketsEntity deleteTicket(BigInteger ticketId){
        TicketsEntity ticketsEntity = null;
        try {
            Session session = HibernateUtil.getSession();
            session.beginTransaction();
            Query query = session.createQuery("from TicketsEntity where ticketId = :ticketId");
            query.setParameter("ticketId", ticketId);
            ticketsEntity = (TicketsEntity) query.list().get(0);
            session.delete(ticketsEntity);
            if (session.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
                session.getTransaction().commit();
            }
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticketsEntity;
    }
}
