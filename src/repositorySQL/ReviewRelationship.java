package repositorySQL;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import domain.Profile;
import domain.Review;


/**
 * This class is used to easily persist Integer relationships to database as
 * an alternative to using native querys due to time and framework limitations.
 * @author nacho
 *
 */
@DatabaseTable
class ReviewRelationship {

    @DatabaseField( id = true )
    private Integer reviewId;

    @DatabaseField
    private Integer fromUser;

    @DatabaseField
    private Integer toUser;

    protected ReviewRelationship(){

    }

    protected ReviewRelationship(Review rvw){
        this.reviewId = rvw.getId();
        this.fromUser = rvw.getprofileOrigin().getUsrId();
        this.toUser = rvw.getprofileTarget().getUsrId();
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public Integer getFrom() {
        return fromUser;
    }

    public Integer getTo() {
        return toUser;
    }

}
