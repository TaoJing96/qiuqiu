package qiuqiu.service.impl.actors;

import qiuqiu.enums.ActionEnum;

/**
 * @author Jing Tao
 * @date 2025/9/28 15:57
 */
public interface Actor {

    String act(String content, String user);

    ActionEnum getActionType();
}
