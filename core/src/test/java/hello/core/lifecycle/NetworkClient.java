package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/*초창기에 사용했던 방법
public class NetworkClient  implements InitializingBean, DisposableBean {*/

public class NetworkClient{

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call = " + url + " message = " + message);
    }

    //생성자 종료시 호출
    @PreDestroy
    public void disconnect() {
        System.out.println("close = " + url);
    }

    //의존관계 주입이 끝나면 실행하는 메서드
    @PostConstruct
    public void init(){
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");

    }


    public void close(){
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
