package hello.core.singleton;

public class SingletonService {

    private static final SingletonService  instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    // 이렇게 private 생성자로 막아버리면 SingleToneTest의 pvsm문의 생성자에서 오류가 나게 된다.
    // 이런 방법으로 여러개의 객체 생성을 막는다.
    private SingletonService(){
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출 ");
    }
}
