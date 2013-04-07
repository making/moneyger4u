package am.ik.moneyger4u.common.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class HibernateAwareObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = 1L;

    public HibernateAwareObjectMapper() {
        Hibernate4Module hm = new Hibernate4Module();
        registerModule(hm);
        disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    public void setPrettyPrint(boolean prettyPrint) {
        configure(SerializationFeature.INDENT_OUTPUT, prettyPrint);
    }
}
