package qiuqiu.service.actors;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import qiuqiu.enums.ActionEnum;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jing Tao
 * @date 2025/9/28 15:59
 */
@Component
public class ActorRegistry {

    @Resource
    private ApplicationContext applicationContext;

    private Map<ActionEnum, Actor> actionEnumActorMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        Map<String, Actor> beansOfType = applicationContext.getBeansOfType(Actor.class);
        for (Map.Entry<String, Actor> stringActorEntry : beansOfType.entrySet()) {
            actionEnumActorMap.put(stringActorEntry.getValue().getActionType(), stringActorEntry.getValue());
        }
    }

    public Actor getActor(ActionEnum actionEnum) {
        Actor actor = actionEnumActorMap.get(actionEnum);
        if (actor == null) {
            throw new RuntimeException("invalid actionEnum:" + actionEnum.toString());
        }
        return actor;
    }
}
