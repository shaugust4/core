package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// 최근에는 생성자를 딱 1개 두고,
// @Autowired 생략하는 방법을 주로 사용.
// 여기에 Lombok 라이브러리의 @RequiredArgsConstructor와 함께 사용하면 기능은 다 제공되면서,
// 코드를 깔끔하게 사용할 수 있다.
public class OrderServiceImpl implements OrderService{

    // 테스트 용
    /*생성자 주입을 선택하는 이유
    * 1. 불변
    *  1) 대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료 시점까지 의존관계를 변경할 일이 없다.
    * 오히려 대부분의 의존관계는 종료 전까지 변하면 안된다.
    *  2) 수정자 주입을 사용하면, setXxx 메서드를 public으로 열어두어야 함.
    *  누군가 실수로 변경할 수도 있고, 변경하면 안되는 메서드를 열어두는 것은 좋은 설계 방법이 아니다.
    * 2. 누락
    *  1) final 키워드를 사용해서 혹시라도 생성자에서 값이 누락되는 오류를 컴파일 시점에서 파악할 수 있다.
    * <참고> 수정자 주입을 포함한 나머지 주입 방식은 생성자 이후에 호출되므로, 필드에 'final' 키워드를 사용할 수 없다.
    **/
    @Getter
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}


