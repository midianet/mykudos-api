package midianet.mykudos.infra.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import midianet.mykudos.infra.helper.MessageHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingDebugAspect {

    private final MessageHelper messageHelper;

    @Before("execution(* midianet.mykudos.application.usecase..*(..)))")
    public void beforeMethod(JoinPoint joinPoint) {
        log.trace(messageHelper.getMessage("app.message.initmethod", joinPoint.getSignature().toShortString()));
    }

    @AfterReturning("execution(* midianet.mykudos.application.usecase..*(..)))")
    public void afterMethod(JoinPoint joinPoint) {
        log.trace(messageHelper.getMessage("app.message.finishmethod", joinPoint.getSignature().toShortString()));
    }

}
