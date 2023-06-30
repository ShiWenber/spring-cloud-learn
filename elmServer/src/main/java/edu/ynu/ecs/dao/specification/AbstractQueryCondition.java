package edu.ynu.ecs.dao.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * 查询条件
 */
public class AbstractQueryCondition {

	List<QueryCondition> _lst;

	private List<QueryCondition> getCondition() {
		return _lst;
	}

	public AbstractQueryCondition(List<QueryCondition> lst) {
		_lst = lst;
	}

	/**
	 */
	public Specification builderCondition() {
		var sb = SpecificationBuilder.builder();
		for (QueryCondition condition : getCondition()) {
			if (condition.getCondition().equals("like")) {
				sb.andLike(condition.getProperty(), condition.getValue().toString());
			}
			else if (condition.getCondition().equals("equal")) {
				sb.andEqual(condition.getProperty(), condition.getValue());
			}
			else if (condition.getCondition().equals("notEqual")) {
				sb.andNotEqual(condition.getProperty(), condition.getValue());
			}
//			else if (condition.getCondition().equals("greaterThan")) {
//				sb.andGreaterThan(condition.getProperty(), condition.getValue());
//			}
//			else if (condition.getCondition().equals("lessThan")) {
//				sb.andLessThan(condition.getProperty(), condition.getValue());
//			}
//			else if (condition.getCondition().equals("greaterThanOrEqual")) {
//				sb.andGreaterThanOrEqual(condition.getProperty(), condition.getValue());
//			}
//			else if (condition.getCondition().equals("lessThanOrEqual")) {
//				sb.andLessThanOrEqual(condition.getProperty(), condition.getValue());
//			}
			else if (condition.getCondition().equals("in")) {
				sb.andIn(condition.getProperty(), (List) condition.getValue());
			}
//			else if (condition.getCondition().equals("notIn")) {
//				sb.andNotIn(condition.getProperty(), (List) condition.getValue());
//			}
//			else if (condition.getCondition().equals("between")) {
//				sb.andBetween(condition.getProperty(), (List) condition.getValue());
//			}
//			else if (condition.getCondition().equals("notBetween")) {
//				sb.andNotBetween(condition.getProperty(), (List) condition.getValue());
//			}
//			else if (condition.getCondition().equals("isNull")) {
//				sb.andIsNull(condition.getProperty());
//			}
//			else if (condition.getCondition().equals("isNotNull")) {
//				sb.andIsNotNull(condition.getProperty());
//			}
		}
		return sb.build();
	}

}
