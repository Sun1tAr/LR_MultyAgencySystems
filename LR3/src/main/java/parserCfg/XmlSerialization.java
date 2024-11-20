package parserCfg;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Optional;

public class XmlSerialization {

    public static <T> Optional<T> deserialize(String path, Class<T> clazz) {
        File f = new File(path);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            T cfg = (T) unmarshaller.unmarshal(f);
            return Optional.ofNullable(cfg);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
