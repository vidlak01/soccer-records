/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.tests;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.Test;
import soccer.records.PersistenceAppContext;
import soccer.records.dao.PlayerResultDao;
import soccer.records.entity.Match;
import soccer.records.entity.Player;
import soccer.records.entity.PlayerResult;
import soccer.records.entity.Team;
import soccer.records.enums.PlayerPost;
import soccer.records.services.MatchService;
import soccer.records.services.PlayerResultServiceImpl;
import soccer.records.services.TeamServiceImpl;
import soccer.records.services.MatchServiceImpl;
import soccer.records.services.PlayerResultService;
import soccer.records.services.PlayerService;
import soccer.records.services.PlayerServiceImpl;
import soccer.records.services.TeamService;

/**
 * Service tests
 * 
 * @author Michaela Bocanova
 */

@ContextConfiguration(classes = PersistenceAppContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PlayerResultTest extends AbstractTestNGSpringContextTests {
   
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    @Mock
    private PlayerResultDao playerResultDao;
    
    @Autowired
    private PlayerResultService playerResultService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private PlayerService playerService;
        
    // helper methods with sample data
    private Player newPlayerA() {
        Player p = new Player();
        p.setName("J�n");
        p.setAge(22);
        p.setCaptian(false);
        p.setSurname("Such�");
        p.setPost(PlayerPost.GOLMAN);
        
        return p;
    }
    private Player newPlayerB() {
        Player p = new Player();
        p.setName("Igor");
        p.setAge(21);
        p.setCaptian(false);
        p.setSurname("Vysok�");
        p.setPost(PlayerPost.GOLMAN);
        
        return p;
    }
    private Team newTeamA() {
        Team t = new Team();
        t.setName("A");
        
        return t;
    }    
    private Team newTeamB() {
        Team t = new Team();
        t.setName("H");
        
        return t;
    }
       
    /**
     * Creates a new result and checks if exists
     */
    @Test
    public void createAndFindPlayerResult() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
           
        Team tA = newTeamA();
        Team tB = newTeamB();
        teamService.create(tA);        
        teamService.create(tB);
        Player pA =newPlayerA();
        pA.setTeam(tA);
        playerService.create(pA);
        Match m = new Match();
        m.setTeamAway(tA);
        m.setTeamHome(tB);
        matchService.create(m);
        PlayerResult pr = new PlayerResult(pA, m);
        pr.setGoalsScored(2);
        
        playerResultService.create(pr);        
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 1);

        Assert.assertEquals(pr, rows.get(0));

        em.getTransaction().commit();
        em.close();
    }
    
    /**
     * Updates a result
     */
    @Test
    public void createAndUpdatePlayerResult() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
          
        Team tA = newTeamA();
        Team tB = newTeamB();
        teamService.create(tA);        
        teamService.create(tB);
        Player pA =newPlayerA();
        pA.setTeam(tA);
        playerService.create(pA);
        Match m = new Match();
        m.setTeamAway(tA);
        m.setTeamHome(tB);
        matchService.create(m);
        PlayerResult pr = new PlayerResult(pA, m);
        pr.setGoalsScored(2);
        
        playerResultService.create(pr);
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 1);
        pr.setGoalsScored(3);
        playerResultService.update(pr);
        List<PlayerResult> rows2 = playerResultService.findAll();
        Assert.assertEquals(rows2.size(), 1);
        
        Assert.assertEquals(rows2.get(0), pr);
        
        em.getTransaction().commit();
        em.close();
    }
    
    /**
     * Deletes a result
     */
    @Test
    public void createAndDeletePlayerResult() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            
        Team tA = newTeamA();
        Team tB = newTeamB();
        teamService.create(tA);        
        teamService.create(tB);
        Player pA =newPlayerA();
        pA.setTeam(tA);
        playerService.create(pA);
        Match m = new Match();
        m.setTeamAway(tA);
        m.setTeamHome(tB);
        matchService.create(m);
        PlayerResult pr = new PlayerResult(pA, m);
        pr.setGoalsScored(2);   
        
        playerResultService.create(pr);
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 1);
        
        playerResultService.delete(pr);
        List<PlayerResult> rows2 = playerResultService.findAll();
        Assert.assertEquals(rows2.size(), 0);
        
        em.getTransaction().commit();
        em.close();
    }
    
    /**
     * Finds results of a specific player
     */
    @Test
    public void createAndFindByPlayer() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            
        Team tA = newTeamA();
        Team tB = newTeamB();
        teamService.create(tA);        
        teamService.create(tB);
        Player pA =newPlayerA();
        pA.setTeam(tA);
        playerService.create(pA);
        Match m = new Match();
        m.setTeamAway(tA);
        m.setTeamHome(tB);
        matchService.create(m);
        PlayerResult pr = new PlayerResult(pA, m);
        pr.setGoalsScored(2);     
        
        playerResultService.create(pr);
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 1);
        
        List<PlayerResult> rows2 = playerResultService.findByPlayer(pr.getPlayer());
        Assert.assertEquals(rows2.size(), 1);
        Assert.assertEquals(rows2.get(0), pr);
        
        em.getTransaction().commit();
        em.close();
    }
    
    /**
     * Finds results for specific match
     */
    @Test
    public void createAndFindByMatch() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            
        Team tA = newTeamA();
        Team tB = newTeamB();
        teamService.create(tA);        
        teamService.create(tB);
        Player pA =newPlayerA();
        pA.setTeam(tA);
        playerService.create(pA);
        Match m = new Match();
        m.setTeamAway(tA);
        m.setTeamHome(tB);
        matchService.create(m);
        PlayerResult pr = new PlayerResult(pA, m);
        pr.setGoalsScored(2);  
        
        playerResultService.create(pr);
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 1);
        
        List<PlayerResult> rows2 = playerResultService.findByMatch(pr.getMatch());
        Assert.assertEquals(rows2.size(), 1);
        Assert.assertEquals(rows2.get(0), pr);
        
        em.getTransaction().commit();
        em.close();
    }
    
    /**
     * Finds results of player of specific match
     */
    @Test
    public void createAndFindByPlayerAndMatch() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            
        Team tA = newTeamA();
        Team tB = newTeamB();
        teamService.create(tA);        
        teamService.create(tB);
        Player pA =newPlayerA();
        pA.setTeam(tA);
        playerService.create(pA);
        Match m = new Match();
        m.setTeamAway(tA);
        m.setTeamHome(tB);
        matchService.create(m);
        PlayerResult pr = new PlayerResult(pA, m);
        pr.setGoalsScored(2);   
        
        playerResultService.create(pr);
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 1);
        
        PlayerResult row2 = playerResultService.findByBoth(pr.getPlayer(), pr.getMatch());
        Assert.assertEquals(row2, pr);
        
        em.getTransaction().commit();
        em.close();
    }
    
    /*@Test
    public void createAndFindByIdPlayerResult() {
 
    }*/
    
    /**
     * Retrieves all results
     */
    @Test
    public void createAndFindAllPlayerResults() {
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
            
        Team tA = newTeamA();
        Team tB = newTeamB();
        teamService.create(tA);        
        teamService.create(tB);
        Player pA =newPlayerA();
        pA.setTeam(tA);
        playerService.create(pA);
        Match m = new Match();
        m.setTeamAway(tA);
        m.setTeamHome(tB);
        matchService.create(m);
        PlayerResult prA = new PlayerResult(pA, m);
        prA.setGoalsScored(2);
        
        Player pB =newPlayerB();
        pB.setTeam(tB);
        playerService.create(pB);
        PlayerResult prB = new PlayerResult(pB, m);
        prB.setGoalsScored(1);
        
        playerResultService.create(prA);
        playerResultService.create(prB);
        List<PlayerResult> rows = playerResultService.findAll();
        Assert.assertEquals(rows.size(), 2);
                
        em.getTransaction().commit();
        em.close();
        
    }
}
