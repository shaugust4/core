package hello.core.singleton;

public class SingletonService {
    /*1. 먼저 static 영역에 객체 instance를 선언한다.*/
    private static final SingletonService instance = new SingletonService();

    /*2. 이 객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회할 수 있음.
    이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.*/
    public static SingletonService getInstance() {
        return instance;
    }

    /*3. 딱 하나의 객체 인스턴스만 존재해야 하므로, 생성자를 private로 막아서 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 방지한다.*/
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글통 객체 로직 호출");
    }
}
