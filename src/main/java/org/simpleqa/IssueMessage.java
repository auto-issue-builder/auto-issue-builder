package org.simpleqa;

import lombok.Builder;
import lombok.Getter;

@Getter
public class IssueMessage {
    private String githubRepoPath;
    private String nickname;
    private String title;
    private String content;

    public IssueMessage() {
    }

    @Builder
    public IssueMessage(String githubRepoPath, String nickname, String title, String content) {
        this.githubRepoPath = githubRepoPath;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
    }
}
