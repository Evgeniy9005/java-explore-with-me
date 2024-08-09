package ru.practicum.data;

import dto.ViewStats;
import lombok.extern.slf4j.Slf4j;
import ru.practicum.admin.dto.UpdateEventAdminRequest;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.category.model.Category;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.constants.State;
import ru.practicum.constants.StateAction;
import ru.practicum.constants.StatusRequest;
import ru.practicum.events.dto.EventFullDto;
import ru.practicum.events.dto.EventShortDto;
import ru.practicum.events.dto.NewEventDto;
import ru.practicum.events.model.Event;
import ru.practicum.events.model.Location;
import ru.practicum.users.dto.UserShortDto;
import ru.practicum.users.model.User;
import ru.practicum.users.request.NewUserRequest;
import ru.practicum.users.request.model.ParticipationRequest;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Slf4j
public class Data {

    public static final LocalDateTime CREATED_DATE = LocalDateTime.of(2024,1,1,1,1);
    public static final LocalDateTime START_DATE = LocalDateTime.of(2024,2,2,2,2);
    public static final LocalDateTime END_DATE = LocalDateTime.of(2024,3,3,3,3);

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    /**
     <p><b>- NewUserRequest</b> баз параметров objects;</p>
     <p><b>- Category</b> баз параметров objects;</p>
     <p><b>- EventShortDto</b> баз параметров objects;</p>
     <p><b>- EventFullDto</b> баз параметров objects;</p>
     <p><b>- NewEventDto</b> баз параметров objects;</p>
     <p><b>- Event</b> c параметрами objects User.class, Category.class;</p>
     <p><b>- ViewStats</b> баз параметров objects;</p>
     <p><b>- ParticipationRequest</b> c параметрами objects Event.class, User.class;</p>

     */
    public static <T> List<T> generationData(Integer createObjects, Type t, Object... objects) {

        return (List<T>) IntStream.iterate(1,i -> i + 1)
                        .mapToObj(i -> getData(i, t, objects))
                        .limit(createObjects)
                        .collect(Collectors.toList());
    }

    private static <D> D getData(int i, Type type, Object... objects) {

        if (type.equals(ViewStats.class)) {
            return (D) new ViewStats("app","uri " + i, Long.valueOf(i));
        }

        if (type.equals(User.class)) {
            return (D) User.builder()
                    .id(i)
                    .name("Пользователь" + i)
                    .email("email" + i + "@mail.ru")
                    .build();
        }

        if (type.equals(Category.class)) {
            return (D) Category.builder()
                    .id(i)
                    .name("Категория" + i)
                    .build();
        }

        if (type.equals(EventShortDto.class)) {
            return (D) EventShortDto.builder()
                    .id(i)
                    .title("Заголовок " + i)
                    .category(new CategoryDto(i,"Категория " + i))
                    .eventDate(LocalDateTime.now().minusDays(1).format(formatter))
                    .initiator(new UserShortDto(i,"Пользователь" + i))
                    .annotation("Краткое описание " + i)
                    .confirmedRequests(i + 1)
                    .paid(true)
                    .views(i * 10)
                    .build();
        }

        if (type.equals(EventFullDto.class)) {
            return (D) EventFullDto.builder()
                    .id(i)
                    .title("Заголовок " + i)
                    .category(new CategoryDto(i,"Категория " + i))
                    .eventDate(LocalDateTime.now().plusDays(1).format(formatter))
                    .initiator(new UserShortDto(i,"Пользователь" + i))
                    .annotation("Краткое описание " + i)
                    .confirmedRequests(i + 1)
                    .paid(true)
                    .views(i * 10)
                    .location(new Location(52.2f,54.4f))
                    .state(State.PUBLISHED)
                    .description("Полное описание события " + i)
                    .participantLimit(0)
                    .requestModeration(false)
                    .publishedOn(LocalDateTime.now().plusMinutes(3).format(formatter))
                    .createdOn(LocalDateTime.now().format(formatter))
                    .build();
        }

        if (type.equals(Event.class)) {
                if (typeCheck(Event.class,objects,User.class,Category.class)) {
                return (D) Event.builder()
                    .id(i)
                    .title("Заголовок " + i)
                    .category((Category) objects[1])
                    .eventDate(LocalDateTime.now().plusDays(1))
                    .initiator((User) objects[0])
                    .annotation("Краткое описание " + i)
                    .confirmedRequests(i + 1)
                    .paid(true)
                    .views(i * 10)
                    .lat(50)
                    .lon(50)
                    .state(State.PUBLISHED)
                    .description("Полное описание события " + i)
                    .participantLimit(0)
                    .requestModeration(false)
                    .publishedOn(LocalDateTime.now().plusMinutes(3))
                    .createdOn(LocalDateTime.now())
                    .build();
                }
        }

        if (type.equals(NewEventDto.class)) {
            return (D) NewEventDto.builder()
                    .title("Заголовок " + i)
                    .category(i)
                    .eventDate(LocalDateTime.now().plusDays(1).format(formatter))
                    .annotation("Краткое описание " + i)
                    .paid(true)
                    .location(new Location(52.2f,54.4f))
                    .description("Полное описание события " + i)
                    .participantLimit(0)
                    .requestModeration(false)
                    .build();
        }

        if (type.equals(ParticipationRequest.class)) {
            if (typeCheck(ParticipationRequest.class,objects,Event.class,User.class)) {
                return (D) ParticipationRequest.builder()
                        .event((Event) objects[0])
                        .created(LocalDateTime.now())
                        .requester((User) objects[1])
                        .status(StatusRequest.PENDING)
                        .build();
            }
        }

        if (type.equals(UpdateEventAdminRequest.class)) {
            return (D) UpdateEventAdminRequest.builder()
                    .title("Заголовок " + i)
                    .annotation("Краткое описание, аннотация " + i)
                    .category(i)
                    .eventDate(LocalDateTime.now().plusDays(1).format(formatter))
                    .paid(true)
                    .location(new Location(52.2f,54.4f))
                    .description("Полное описание события, описание " + i)
                    .participantLimit(0)
                    .requestModeration(false)
                    .stateAction(StateAction.PUBLISH_EVENT.toString())
                    .build();
        }

        if (type.equals(Category.class)) {
            return (D) Category.builder()
                    .id(i)
                    .name("Категория" + i)
                    .build();
        }

        if (type.equals(NewUserRequest.class)) {
            return (D) new NewUserRequest("email@mail" + i, "User" + i);
        }

        if (type.equals(Compilation.class)) {
            if (typeCheck(Event.class,objects,List.class)) {
                return (D) Compilation.builder()
                        .id(i)
                        .events(String.valueOf((List<Integer>) objects[0]))
                        .pinned(true)
                        .title("Заголовок" + i)
                        .build();
            }
        }


        return null;
    }

    public static <T> void printList(Collection<T> list, String separator) {
        System.out.println(separator.repeat(33));
        list.stream().forEach(System.out::println);
        System.out.println(separator.repeat(33));
    }

    public static <T> void printList(Collection<T> list) {
        System.out.println("~*~".repeat(33));
        list.stream().forEach(System.out::println);
        System.out.println("~*~".repeat(33));
    }

    /**
     <p><b>- Формат </b> "yyyy-MM-dd HH:mm:ss"</p>
     */
    public static DateTimeFormatter getFormatter() {
        return formatter;
    }

    public static DateTimeFormatter getMyFormatter(String formatter) {
        return DateTimeFormatter.ofPattern(formatter);
    }

    public static void separato__________________________________r() {
        System.out.println("***************************************************************");
    }

    private static boolean typeCheck(Type generated, Object[] objects, Type t1, Type t2) {

        if (objects.length == 2) {
            if (objects[0].getClass().equals(t1) && objects[1].getClass().equals(t2)) {
                return true;
            } else {
                System.out.println(
                        String.format("Входные параметры должен быть 2 t1 = %s, t2 = %s для создания %s!",t1,t2,generated));
            return false;
            }

        } else {
            System.out.println(
                    String.format("Входные параметры должен быть 2 t1 = %s, t2 = %s для создания %s!",t1,t2,generated));
            return false;
        }
    }

    private static boolean typeCheck(Type generated, Object[] objects, Type t1) {

        if (objects.length == 1) {
            if (objects[0].getClass().equals(t1)) {
                return true;
            } else {
                System.out.println(
                        String.format("Входные параметры должен быть 1 t1 = %s для создания %s!",t1,generated));
                return false;
            }

        } else {
            System.out.println(
                    String.format("Входные параметры должен быть 1 t1 = %s для создания %s!",t1,generated));
            return false;
        }
    }
}
