package GRUPO1.TP;

import GRUPO1.TP.entities.*;
import GRUPO1.TP.repositories.*;
import GRUPO1.TP.repositories.ExerciseRepository;
import GRUPO1.TP.repositories.LevelRepository;
import GRUPO1.TP.repositories.StudentRepository;
import GRUPO1.TP.repositories.PlanRepository;
import GRUPO1.TP.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TPApplication {

    public static void main(String[] args) {
        SpringApplication.run(TPApplication.class, args);
    }
    @Bean
    public CommandLineRunner mappingDemo(
            UserRepository userRepository,
            LevelRepository levelRepository,
            StudentRepository studentRepository,
            PlanRepository planRepository,
            ExerciseRepository exerciseRepository,
            ExerciseImageRepository exerciseImageRepository,
            LessonRepository lessonRepository,
            LessonStudentRepository lessonStudentRepository,
            AuthorityRepository authorityRepository,
            ImageRepository imageRepository,
            StudentPlanRepository studentPlanRepository,
            StudentExerciseRepository studentExerciseRepository

    ){
        return args -> {
            authorityRepository.save(new Authority(AuthorityName.ROLE_ADMIN));
            authorityRepository.save(new Authority(AuthorityName.ROLE_STUDENT));
            authorityRepository.save(new Authority(AuthorityName.ROLE_PRINCIPAL));

            authorityRepository.saveAll(
                    List.of(
                            new Authority(AuthorityName.READ),
                            new Authority(AuthorityName.WRITE)
                    )
            );
            //REGISTROS



            //USERS
            User user1 =  userRepository.save(new User(Long.valueOf(0),
                            "Aldair", new BCryptPasswordEncoder().encode("123"), true,new Date(),null,
                            List.of(
                                    authorityRepository.findByName(AuthorityName.ROLE_ADMIN)
                            )
                    )
            );
            User user2 =  userRepository.save(new User(Long.valueOf(0),
                            "Salvador", new BCryptPasswordEncoder().encode("123"), true,new Date(),null,
                            List.of(
                                    authorityRepository.findByName(AuthorityName.ROLE_ADMIN)
                            )
                    )
            );
            User user3 =  userRepository.save(new User(Long.valueOf(0),
                            "Niels", new BCryptPasswordEncoder().encode("123"), true,new Date(),null,
                            List.of(
                                    authorityRepository.findByName(AuthorityName.ROLE_ADMIN)
                            )
                    )
            );
            User user4 =  userRepository.save(new User(Long.valueOf(0),
                            "Nemberk", new BCryptPasswordEncoder().encode("123"), true,new Date(),null,
                            List.of(
                                    authorityRepository.findByName(AuthorityName.ROLE_ADMIN)
                            )
                    )
            );
            User user5 =  userRepository.save(new User(Long.valueOf(0),
                            "Keo", new BCryptPasswordEncoder().encode("123"), true,new Date(),null,
                            List.of(
                                    authorityRepository.findByName(AuthorityName.ROLE_STUDENT)
                            )
                    )
            );



            Level level1 =  levelRepository.save(new Level(Long.valueOf(0), "Conocedor", "Nivel Conocedor",null));
            Level level2 =  levelRepository.save(new Level(Long.valueOf(0), "Entusiasta", "Nivel Entusiasta",null));
            Level level3 =  levelRepository.save(new Level(Long.valueOf(0), "Principiante", "Nivel Principiante",null));
            Level level4 =  levelRepository.save(new Level(Long.valueOf(0), "Avanzado", "Nivel Avanzado",null));


            Plan plan1 = planRepository.save(new Plan(Long.valueOf(0), "Fremium", "Plan fremium", 10.0, 180L,null ));
            Plan plan2 = planRepository.save(new Plan(Long.valueOf(0), "Estudiante", "Plan estudiante", 150.0, 40L, null ));
            Plan plan3 = planRepository.save(new Plan(Long.valueOf(0), "Premium", "Plan premium", 200.00, 40L,null));

            Lesson lesson1 = lessonRepository.save(new Lesson(Long.valueOf(0),"Saludos y Despedidas", "En esta lección, aprenderemos los signos básicos para saludar y despedirnos. ¡Prepárate para dar la bienvenida con una sonrisa! \uD83D\uDE0A\n", null, null,null));
            Lesson lesson2 = lessonRepository.save(new Lesson(Long.valueOf(0), "Números y Letras", "En esta lección, practicaremos los signos para los números y las letras. ¡Vamos a contar y deletrear con entusiasmo! \uD83D\uDD22\uD83D\uDD20",null,null,null));
            Lesson lesson3 = lessonRepository.save(new Lesson(Long.valueOf(0), "Expresiones Cotidianas", "En esta lección, crearemos frases sencillas utilizando el lenguaje de señas. ¡Comunica con tus manos y corazón! \uD83E\uDD1F❤\uFE0F\n",null ,null ,null));






            Student student1 = studentRepository.save(new Student(0L, "Aldair", "Aldair@upc.edu.pe", "995361755", "https://acortar.link/Fn7l8Y", level1, user1, null, null, null, null));
            Student student2 = studentRepository.save(new Student(0L, "Nemberk", "Nemberk@upc.edu.pe", "987654123", "https://acortar.link/4sUaLT", level2, user4, null, null, null, null));
            Student student3 = studentRepository.save(new Student(0L, "Niels", "Niels@upc.edu.pe", "965812561", "https://acortar.link/MYFB3O", level1, user3, null, null, null, null));
            Student student4 = studentRepository.save(new Student(0L, "Salvador", "Salvador@upc.edu.pe", "956321587", "https://acortar.link/ngJ5IM", level4, user2, null, null, null, null));
            Student student5 = studentRepository.save(new Student(0L, "Keo", "keo@upc.edu.pe", "96581234", "https://acortar.link/vIMZMK", level3, user5, null, null, null, null));

            StudentPlan studentPlan1 = studentPlanRepository.save(new StudentPlan(0L, new Date(), new Date(), student1, plan1));
            StudentPlan studentPlan2 = studentPlanRepository.save(new StudentPlan(0L, new Date(), new Date(), student2, plan2));
            StudentPlan studentPlan3 = studentPlanRepository.save(new StudentPlan(0L, new Date(), new Date(), student3, plan3));
            StudentPlan studentPlan4 = studentPlanRepository.save(new StudentPlan(0L, new Date(), new Date(), student4, plan2));
            StudentPlan studentPlan5 = studentPlanRepository.save(new StudentPlan(0L, new Date(), new Date(), student5, plan2));


            LessonStudent lessonStudent1=lessonStudentRepository.save(new LessonStudent(Long.valueOf(0), "En progreso",lesson2,student3));
            LessonStudent lessonStudent2=lessonStudentRepository.save(new LessonStudent(Long.valueOf(0), "Terminado",lesson1,student1));
            LessonStudent lessonStudent3=lessonStudentRepository.save(new LessonStudent(Long.valueOf(0), "Sin iniciar",lesson3,student1));

            LessonStudent lessonStudent4=lessonStudentRepository.save(new LessonStudent(Long.valueOf(0), "En progreso",lesson1,student5));




            //Lesson1
            Exercise exercise1=exerciseRepository.save(new Exercise(Long.valueOf(0), "Avanzado", "Completar", "¿Cúal es el significado de esta seña ?\uD83E\uDD14",//hola
                    "¡Inténtalo de nuevo! El saludo correcto es el signo de \"hola\". \uD83D\uDCAA",null,null,lesson1 ));
            Exercise exercise2=exerciseRepository.save(new Exercise(Long.valueOf(0), "Medio", "Marcar", "Cómo sería en señas: 'Adiós, hasta luego' ",
                    "¡Sigue practicando! El signo correcto para \"adiós\" es importante. \uD83D\uDCAA",null,null,lesson1 ));
            Exercise exercise3=exerciseRepository.save(new Exercise(Long.valueOf(0), "Basico", "Marcar", "Cómo sería en señas: 'Buen día.' ",
                    "¡Casi lo tienes! Recuerda el signo para \"buen día\". \uD83D\uDCAA",null,null,lesson1));
            Exercise exercise4=exerciseRepository.save(new Exercise(Long.valueOf(0), "Basico", "Completar", "¿Cúal es el significado de esta seña ?\uD83E\uDD14",//buenas noches
                    "¡Inténtalo de nuevo! El signo correcto para \"buenas noches\" es importante. \uD83D\uDCAA",null,null,lesson1));
            //Lesson2
            Exercise exercise5=exerciseRepository.save(new Exercise(Long.valueOf(0), "Basico", "Completar", "¿Cúal es el significado de esta seña ?\uD83E\uDD14",
                    "¡Sigue intentándolo! El signo correcto para el número 5 es importante. \uD83D\uDCAA\n",null,null,lesson2));
            Exercise exercise6=exerciseRepository.save(new Exercise(Long.valueOf(0), "Basico", "Marcar", "¿Cómo sería en señas?: Tres gatos",
                    "¡Sigue practicando! El signo correcto para el número 3 es importante. \uD83D\uDCAA",null,null,lesson2));
            Exercise exercise7=exerciseRepository.save(new Exercise(Long.valueOf(0), "Medio", "Completar", "¿Cúal es el significado de esta seña ?\uD83E\uDD14",//A
                    "¡Casi lo tienes! La letra \"A\" se representa con un signo específico. \uD83D\uDCAA",null,null,lesson2));
            Exercise exercise8=exerciseRepository.save(new Exercise(Long.valueOf(0), "Basico", "Marcar", "¿Cómo sería en señas?: La letra C ",//C
                    "¡Inténtalo de nuevo! El signo correcto para la letra \"C\" es importante. \uD83D\uDCAA",null,null,lesson2));
            //Lesson3
            Exercise exercise9=exerciseRepository.save(new Exercise(Long.valueOf(0), "Medio", "Completar", "¿Cúal es el significado de esta seña ?\uD83E\uDD14",
                    "¡Vas por buen camino! Recuerda los signos para \"cómo\" y \"estás\". \uD83D\uDCAA",null,null,lesson3));
            Exercise exercise10=exerciseRepository.save(new Exercise(Long.valueOf(0), "Basico", "Completar", "¿Cúal es el significado de esta seña ?\uD83E\uDD14",
                    "¡Sigue practicando! El signo correcto para \"soy\" y  \"yo\" son importantes. \uD83D\uDCAA\n",null,null,lesson3));
            Exercise exercise11=exerciseRepository.save(new Exercise(Long.valueOf(0), "Basico", "Marcar", "¿Cómo sería en señas?: Gracias ",
                    "¡Sigue intentándolo! El signo correcto para \"gracias\" es fundamental. \uD83D\uDCAA",null,null,lesson3));
            Exercise exercise12=exerciseRepository.save(new Exercise(Long.valueOf(0), "Medio", "Marcar", "¿Cómo sería en señas?: ¿Necesitas ayuda?",
                    "¡Vas por buen camino! Recuerda los signos para \"necesitas\" y \"ayuda\". \uD83D\uDCAA",null,null,lesson3));
            //Lesson4


            //lesson1
            Image image1 = imageRepository.save(new Image(Long.valueOf(0),"https://previews.123rf.com/images/kayocci/kayocci2006/kayocci200600044/149618138-lenguaje-de-se%C3%B1as-internacional-hola-hombre.jpg","Hola",null));
            Image image2 = imageRepository.save(new Image(Long.valueOf(0),"https://previews.123rf.com/images/kayocci/kayocci2006/kayocci200600041/149355865-lenguaje-de-se%C3%B1as-internacional-adi%C3%B3s-hombre.jpg","Adios",null));
            Image image3 = imageRepository.save(new Image(Long.valueOf(0),"https://pbs.twimg.com/media/EyJhCLCXAAEn-NF.jpg","Buenos dias",null));
            Image image4 = imageRepository.save(new Image(Long.valueOf(0),"https://pbs.twimg.com/media/Ev1o463XcAc8VJS.jpg","Buenas noches",null));

            //lesson2
            Image image5 = imageRepository.save(new Image(Long.valueOf(0),"https://previews.123rf.com/images/stockseller/stockseller2009/stockseller200908153/155578331-lenguaje-de-se%C3%B1as-un-collage-del-n%C3%BAmero-num%C3%A9rico-5-del-lenguaje-de-se%C3%B1as-americano-cinco-por-mano.jpg","5",null));
            Image image6 = imageRepository.save(new Image(Long.valueOf(0),"https://st.depositphotos.com/1029434/4867/v/450/depositphotos_48673857-stock-illustration-sign-languagenumber-3.jpg","3",null));
            Image image7 = imageRepository.save(new Image(Long.valueOf(0),"https://previews.123rf.com/images/pchvector/pchvector2211/pchvector221101908/194247113-mano-que-muestra-la-letra-a-ilustraci%C3%B3n-de-vector-de-alfabeto-de-lenguaje-de-se%C3%B1as-dedo-en.jpg","A",null));
            Image image8 = imageRepository.save(new Image(Long.valueOf(0),"https://previews.123rf.com/images/pchvector/pchvector2211/pchvector221101901/194247086-mano-que-muestra-la-letra-c-lenguaje-de-se%C3%B1as-alfabeto-ilustraci%C3%B3n-vectorial-dedo-en-diferente.jpg","C",null));

            //lesson3
            Image image9 = imageRepository.save(new Image(Long.valueOf(0),"https://blogs.iadb.org/igualdad/wp-content/uploads/sites/33/2022/09/image-8.png","Como estas",null));
            Image image10 = imageRepository.save(new Image(Long.valueOf(0),"https://i.pinimg.com/originals/22/1a/8e/221a8e5211d6489eb2dd6fe486f1fe4c.jpg","Yo soy",null));
            Image image11= imageRepository.save(new Image(Long.valueOf(0),"https://thumbs.dreamstime.com/z/lenguaje-internacional-de-se%C3%B1as-gracias-mujer-material-vectorial-la-ilustraci%C3%B3n-personas-187055381.jpg","Gracias",null));
            Image image12 = imageRepository.save(new Image(Long.valueOf(0),"https://i.pinimg.com/564x/e3/c2/84/e3c28475304dc9a85fdbb8caa740b298.jpg","Necesitas ayuda",null));

            //Imagenes Diccionario
            //Dia a Dia
            Image image13 = imageRepository.save(new Image(Long.valueOf(0),"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCzIM-z4Q3dSA80k4vx4sYNjE9O0D2gog27VTr-1e9gjmJF1vl","1",null));
           //Amigos
            Image image14 = imageRepository.save(new Image(Long.valueOf(0),"https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcStLJfqoWC9juhZgZLbF496nCq4bMYtiBkLD6ppzVDEpEfXNJhy","2",null));
            //Escuela
            Image image15= imageRepository.save(new Image(Long.valueOf(0),"https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTgDFcxNVPJtZbszDY8X5WDnqZRUVbWDRCNzdSXlZPMeJ68yJcA","3",null));
            //Abecedario
            Image image16 = imageRepository.save(new Image(Long.valueOf(0),"https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTV86GcjNR376gydmF2ywPgt_w9ih76i9VtJ1y0ZmP6SDUjPYPd","4",null));
            //Hogar
            Image image17 = imageRepository.save(new Image(Long.valueOf(0),"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcS_01UqEjwYLZ_GUHxMwlCYcvK9bSGb9Fy9s2Xds08NChPoAIZK","5",null));
            Image image18 = imageRepository.save(new Image(Long.valueOf(0),"https://img.poki.com/cdn-cgi/image/quality=78,width=204,height=204,fit=cover,f=auto/291aaf2dcf5c1322558a9c038b3c5251.png","6",null));

            //LeSSON1

            ExerciseImage exerciseImage1=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"Hola",false,image1, exercise1));
            //Ejercicio para marcar alternativas
            ExerciseImage exerciseImage2=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"Adios",true,image2, exercise2));//correcta
            ExerciseImage exerciseImage14=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"",false,image4, exercise2));
            ExerciseImage exerciseImage15=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"",false,image5, exercise2));
            //Ejercicio para marcar alternativas
            ExerciseImage exerciseImage3=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"",true,image3, exercise3));//correcta
            ExerciseImage exerciseImage17=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"",false,image7, exercise3));
            ExerciseImage exerciseImage18=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"",false,image8, exercise3));


            ExerciseImage exerciseImage4=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"Buenas noches",false,image4, exercise4));

            //Lesson 2

            ExerciseImage exerciseImage5=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"5",true,image5, exercise5));
            //Ejercicio para marcar alternativas
            ExerciseImage exerciseImage6=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"3",true,image6, exercise6));//correcta
            ExerciseImage exerciseImage20=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"",false,image3, exercise6));
            ExerciseImage exerciseImage21=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"",false,image9, exercise6));


            ExerciseImage exerciseImage7=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"A",false,image7, exercise7));
            //Ejercicio para marcar alternativas
            ExerciseImage exerciseImage8=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"C",true,image8, exercise8));//correcta
            ExerciseImage exerciseImage23=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"",false,image12, exercise8));
            ExerciseImage exerciseImage24=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"",false,image11, exercise8));


            //lesson 3
            ExerciseImage exerciseImage9=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"Como estas",false,image9, exercise9));
            ExerciseImage exerciseImage10=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"Yo soy",false,image10, exercise10));
            //Ejercicio para marcar alternativas
            ExerciseImage exerciseImage11=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"Gracias",true,image11, exercise11));//correcta
            ExerciseImage exerciseImage26=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"",false,image2, exercise11));
            ExerciseImage exerciseImage27=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"",false,image3, exercise11));


            //Ejercicio para marcar alternativas
            ExerciseImage exerciseImage12=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"Necesitas ayuda",true,image12, exercise12));//correcta
            ExerciseImage exerciseImage28=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"",false,image5, exercise12));
            ExerciseImage exerciseImage29=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"",false,image9, exercise12));
            ExerciseImage exerciseImage30=exerciseImageRepository.save(new ExerciseImage(Long.valueOf(0),"",false,image10, exercise12));


            StudentExercise studentExercise1=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), true ,exercise1, student1));
            StudentExercise studentExercise2=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), true ,exercise1, student2));
            StudentExercise studentExercise3=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise1, student3));
            StudentExercise studentExercise4=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), true ,exercise1, student4));

            StudentExercise studentExercise5=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise1, student5));
            StudentExercise studentExercise6=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise2, student1));
            StudentExercise studentExercise7=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), true ,exercise2, student2));
            StudentExercise studentExercise8=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), true ,exercise2, student3));
            StudentExercise studentExercise9=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), true ,exercise2, student4));
            StudentExercise studentExercise10=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), true ,exercise2, student5));


            StudentExercise studentExercise11=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise3, student1));
            StudentExercise studentExercise12=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise3, student2));
            StudentExercise studentExercise13=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise3, student3));
            StudentExercise studentExercise14=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise3, student4));
            StudentExercise studentExercise15=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise3, student5));


            StudentExercise studentExercise16=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise4, student1));
            StudentExercise studentExercise17=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise4, student2));
            StudentExercise studentExercise18=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise4, student3));
            StudentExercise studentExercise19=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise4, student4));
            StudentExercise studentExercise20=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise4, student5));


            StudentExercise studentExercise21=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise5, student1));
            StudentExercise studentExercise22=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise5, student2));
            StudentExercise studentExercise23=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise5, student3));
            StudentExercise studentExercise24=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise5, student4));
            StudentExercise studentExercise25=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise5, student5));

            StudentExercise studentExercise26=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise6, student1));
            StudentExercise studentExercise27=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise6, student2));
            StudentExercise studentExercise28=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise6, student3));
            StudentExercise studentExercise29=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise6, student4));
            StudentExercise studentExercise30=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise6, student5));

            StudentExercise studentExercise31=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise7, student1));
            StudentExercise studentExercise32=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise7, student2));
            StudentExercise studentExercise33=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise7, student3));
            StudentExercise studentExercise34=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise7, student4));
            StudentExercise studentExercise35=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise7, student5));

            StudentExercise studentExercise36=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise8, student1));
            StudentExercise studentExercise37=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise8, student2));
            StudentExercise studentExercise38=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise8, student3));
            StudentExercise studentExercise39=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise8, student4));
            StudentExercise studentExercise40=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise8, student5));

            StudentExercise studentExercise41=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise9, student1));
            StudentExercise studentExercise42=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise9, student2));
            StudentExercise studentExercise43=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise9, student3));
            StudentExercise studentExercise44=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise9, student4));
            StudentExercise studentExercise45=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise9, student5));

            StudentExercise studentExercise46=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise10, student1));
            StudentExercise studentExercise47=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise10, student2));
            StudentExercise studentExercise48=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise10, student3));
            StudentExercise studentExercise49=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise10, student4));
            StudentExercise studentExercise50=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise10, student5));

            StudentExercise studentExercise51=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise11, student1));
            StudentExercise studentExercise52=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise11, student2));
            StudentExercise studentExercise53=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise11, student3));
            StudentExercise studentExercise54=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise11, student4));
            StudentExercise studentExercise55=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise11, student5));

            StudentExercise studentExercise56=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise12, student1));
            StudentExercise studentExercise57=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise12, student2));
            StudentExercise studentExercise58=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise12, student3));
            StudentExercise studentExercise59=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise12, student4));
            StudentExercise studentExercise60=studentExerciseRepository.save(new StudentExercise(Long.valueOf(0), new Date(), null ,exercise12, student5));

//            System.out.println("\nLista Completa student");
//            List<Student> listFindAll = studentRepository.findAll();
//            for(Student s: listFindAll) {
//                System.out.println(s);
//            }

//            //Plan Repository
//            System.out.println("\nLista por Nombre y Menor precio");
//            List<Plan> planLowerPrice = planRepository.findByNameAndPrice(200.0);
//            for(Plan e: planLowerPrice) {
//                System.out.println(e);
//            }
////
//            System.out.println("\nLista por rango de precios");
//            List<Plan> planRangePrice = planRepository.findByPriceBetween(140.0, 150.0);
//            for(Plan e: planRangePrice) {
//                System.out.println(e);
//            }
//            //Exercise Repository
//            System.out.println("\nLista por nivel");
//            List<Exercise> levelList = exerciseRepository.findByLevel("Avanzado");
//            for(Exercise e: levelList) {
//                System.out.println(e);
//            }
//
//            System.out.println("\nLista por  lesson ");
//            List<Exercise> listLessonTheme = exerciseRepository.findByLesson("lesson");
//            for(Exercise e: listLessonTheme) {
//                System.out.println(e);
//            }
//
//            System.out.println("\nLista por  tipo de pregunta ");
//            List<Exercise> exercisetype_question = exerciseRepository.findByType_question("PreguntaFacil");
//            for(Exercise e:exercisetype_question){
//                System.out.println(e);
//            }System.out.println("\nLista  por tipo de pregunta");
//
//            //Level Repository
//            System.out.println("\nLista por  level ");
//            List<Level> listlevel = levelRepository.findByName("avanzado");
//            for(Level l: listlevel) {
//                System.out.println(l);
//            }
//
//            //Lesson Repository
//            System.out.println("\nLista por  thema ");
//            List<Lesson> listfindByTheme = lessonRepository.findByTheme("avanzado");
//            for(Lesson l: listfindByTheme) {
//                System.out.println(l);
//            }
//
//            System.out.println("\nLista por  level ");
//            List<Lesson> listfindByDescription = lessonRepository.findByDescription("avanzado");
//            for(Lesson l: listfindByDescription) {
//                System.out.println(l);
//            }
//            //ExerciseImage Repository
//            System.out.println("\nLista por  respuesta correcta ");
//            List<ExerciseImage> listLevelExeImage= exerciseImageRepository.ListLevelExeImage("avanzado");
//            for(ExerciseImage ei: listLevelExeImage) {
//                System.out.println(ei);
//            }
//            System.out.println("\nLista por  level ");
//            List<ExerciseImage> listtypeQuestion = exerciseImageRepository.ListBytypeQuestion("Vocales");
//            for(ExerciseImage ei: listtypeQuestion) {
//                System.out.println(ei);
//            }

        };
    }
}
