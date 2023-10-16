package org.yahve.chat.server.session;

import io.netty.channel.Channel;

import java.util.List;
import java.util.Set;

/**
 * @author m1ggle
 * @project nettys
 * @describe 群聊会话接口
 * @date 2023/10/13
 */
public interface GroupSession {
    /**
     * 创建群组
     * @param name
     * @param members
     * @return
     */
    Group createGroup(String name, Set<String> members);

    /**
     * 加入群组
     * @param username
     * @param member
     * @return
     */
    Group joinMember(String username, String member);

    /**
     * 删除群组
     * @param name
     * @return
     */
    Group removeGroup(String name);

    /**
     * 获取组成员
     * @param name 组名
     * @return 成员集合, 如果群不存在或没有成员会返回 empty set
     */
    Set<String> getMembers(String name);

    /**
     * 获取组成员的 channel 集合, 只有在线的 channel 才会返回
     * @param name 组名
     * @return 成员 channel 集合
     */
    List<Channel> getMembersChannel(String name);

    Group removeMember(String name, String member);

    /**
     * 判断群聊是否一被创建
     * @param name 群聊名称
     * @return 是否存在
     */
    boolean isCreated(String name);
}
