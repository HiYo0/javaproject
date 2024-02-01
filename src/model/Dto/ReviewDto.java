package model.Dto;

public class ReviewDto {
    private int review_pk;
    private int target;
    private int writer;
    private String content;
    private int score;

    public ReviewDto(){}

    public ReviewDto(int review_pk, int target, int writer, String content, int score) {
        this.review_pk = review_pk;
        this.target = target;
        this.writer = writer;
        this.content = content;
        this.score = score;
    }

    public int getReview_pk() {
        return review_pk;
    }

    public void setReview_pk(int review_pk) {
        this.review_pk = review_pk;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getWriter() {
        return writer;
    }

    public void setWriter(int writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ReviewDto{" +
                "review_pk=" + review_pk +
                ", target=" + target +
                ", writer=" + writer +
                ", content='" + content + '\'' +
                ", score=" + score +
                '}';
    }
}
