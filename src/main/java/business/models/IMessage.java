package business.models;

import java.io.Serializable;

public interface IMessage<T extends Object> extends Serializable {

    Employee to();

    String subject();

    T content();

}
