//package com.binodcoder.merokaamapi.controller;
//import com.binodcoder.merokaamapi.repository.JobSeekerProfileRepository;
//import com.binodcoder.merokaamapi.service.JobSeekerProfileService;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.test.context.TestPropertySource;
//
//@TestPropertySource("/application-test.properties")
//@AutoConfigureMockMvc
//@SpringBootTest
//public class JobSeekerProfileServiceTest {
//
//    // inject support utils
//    private static MockHttpServletRequest request;
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//
//    // inject Service and DAOs
//    @Mock
//    JobSeekerProfileService jobSeekerProfileService;
//
//    @Autowired
//    private JdbcTemplate jdbc;
//
//    @Autowired
//    private JobSeekerProfileRepository jobSeekerProfileRepository;
//    // inject SQL strings
//
//    @Value("${sql.script.create.job_profile}")
//    private String sqlCreateJobProfile;
//
//    @Value("${sql.script.delete.job_profile}")
//    private String sqlDeleteJobProfile;
//
//    // @BeforeAll, @BeforeEach and @AfterEach
//
//    @BeforeAll
//    public static void setup(){
//        request=new MockHttpServletRequest();
//        request.setParameter("firstname", "Binod");
//        request.setParameter("lastname", "Bhandari");
//
//    }
//
//
//
//
//
//    @BeforeEach
//    public void setupDatabase(){
//        jdbc.execute(sqlCreateJobProfile);
//    }
//
//    @Test
//    public void placeholder(){
//
//    }
//
//    @AfterEach
//    public void setupAfterTransaction(){
//        jdbc.execute(sqlDeleteJobProfile);
//    }
//}
