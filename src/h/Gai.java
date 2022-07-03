package h;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringReader;

/**
 * @author: jzh
 * @date: created in 2022/3/29
 * @description:
 * @version: 1.0
 */
public class Gai {


    public static void readXml(String xml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Reado.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader sr = new StringReader(xml);
        Reado unmarshal = (Reado) unmarshaller.unmarshal(sr);
        System.out.println(unmarshal);
    }
    @XmlRootElement(name="LoginIn")
    private static class Reado{

        private String LoginId;
        private String Token;
        private String PassWord;
        private String PassPod;

        public String getLoginId() {
            return LoginId;
        }

        public void setLoginId(String loginId) {
            LoginId = loginId;
        }

        public String getToken() {
            return Token;
        }

        public void setToken(String token) {
            Token = token;
        }

        public String getPassWord() {
            return PassWord;
        }

        public void setPassWord(String passWord) {
            PassWord = passWord;
        }

        public String getPassPod() {
            return PassPod;
        }

        public void setPassPod(String passPod) {
            PassPod = passPod;
        }
    }

    public static void main(String[] args) throws JAXBException {

        readXml("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "\t\t<LoginIn xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                "\t\t<LoginId>zhkf5031</LoginId>\n" +
                "\t\t<Token>976bb594bdfe1784873ee5b3508c1b0e</Token>\n" +
                "\t\t<PassWord>zhkf5031</PassWord>\n" +
                "\t\t<PassPod></PassPod>\n" +
                "\t\t</LoginIn>");

    }

}
