package edu.ait.ria.ejb.service;

import edu.ait.ria.ejb.jpa.CarDao;
import edu.ait.ria.ejb.model.Car;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

//todo write tests here
@RunWith(Arquillian.class)
public class ServiceTest {


        @Deployment
        public static Archive<?> createTestArchive() {
            return ShrinkWrap
                    .create(JavaArchive.class, "Test.jar")
                    .addClasses(Car.class, CarDao.class,
                            CarService.class)
                    //	.addPackage(EventCause.class.getPackage())
                    //	.addPackage(EventCauseDAO.class.getPackage())
                    //this line will pick up the production db
                    .addAsManifestResource("META-INF/persistence.xml",
                            "persistence.xml")
                    .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        }


        @EJB
        private Car car;

        @EJB
        private CarDao carDao;

        @EJB
        private CarService carService;

        @Before
        public void setUp() {
            //this function means that we start with an empty table
            //And add one wine
            //it should be possible to test with an in memory db for efficiency

        }

        @Test
        public void testGetAllWines() {

        }

    }
