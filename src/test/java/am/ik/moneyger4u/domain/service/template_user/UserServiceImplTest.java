package am.ik.moneyger4u.domain.service.template_user;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import am.ik.moneyger4u.domain.model.TemplateUser;
import am.ik.moneyger4u.domain.repository.template_user.UserRepository;
import am.ik.moneyger4u.domain.service.template_user.UserServiceImpl;

public class UserServiceImplTest {
    protected UserServiceImpl userService;

    protected UserRepository userRepository;

    protected PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        userService = new UserServiceImpl();
        userRepository = mock(UserRepository.class);
        userService.userRepository = userRepository;
        passwordEncoder = mock(PasswordEncoder.class);
        userService.passwordEncoder = passwordEncoder;
    }

    @Test
    public void testSave() {
        TemplateUser user = new TemplateUser();
        user.setName("foo");

        when(passwordEncoder.encodePassword("password", "foo")).thenReturn(
                "xxxx");

        userService.save(user, "password");

        ArgumentCaptor<TemplateUser> userArg = ArgumentCaptor.forClass(TemplateUser.class);
        verify(userRepository, times(1)).save(userArg.capture());
        TemplateUser actual = userArg.getValue();
        assertThat(actual, is(user));
        assertThat(user.getCreatedAt(), is(notNullValue()));
        assertThat(user.getUpdatedAt(), is(notNullValue()));
        assertThat(user.getPassword(), is("xxxx"));
    }

    @Test
    public void testFindOne() {
        TemplateUser user = new TemplateUser();
        when(userRepository.findOne(100)).thenReturn(user);

        TemplateUser result = userService.findOne(100);

        assertThat(result, is(user));
    }

    @Test
    public void testFindAll() {
        Pageable pageable = new PageRequest(1, 10);
        Page<TemplateUser> page = new PageImpl<TemplateUser>(Arrays.asList(new TemplateUser()));
        when(userRepository.findAll(pageable)).thenReturn(page);

        Page<TemplateUser> result = userService.findAll(pageable);

        assertThat(result, is(page));
    }

    @Test
    public void testFindByNameLike() {
        Pageable pageable = new PageRequest(1, 10);
        Page<TemplateUser> page = new PageImpl<TemplateUser>(Arrays.asList(new TemplateUser()));
        when(userRepository.findByNameLike("foo%", pageable)).thenReturn(page);

        Page<TemplateUser> result = userService.findByNameLike("foo", pageable);

        assertThat(result, is(page));
    }

    @Test
    public void testDelete() {
        TemplateUser user = new TemplateUser();

        userService.delete(user);

        ArgumentCaptor<TemplateUser> userArg = ArgumentCaptor.forClass(TemplateUser.class);
        verify(userRepository, times(1)).delete(userArg.capture());
        assertThat(userArg.getValue(), is(user));
    }

}
