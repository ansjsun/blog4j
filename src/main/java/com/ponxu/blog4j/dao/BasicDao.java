package com.ponxu.blog4j.dao;

import java.util.ArrayList;
import java.util.List;

import org.nutz.castor.Castors;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.entity.MappingField;
import org.nutz.dao.entity.Record;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.cri.SqlExpression;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * 数据操作
 * 
 * @author ansj
 */
@IocBean
public class BasicDao extends NutDao {

	/**
	 * 根据Id删除数据
	 * 
	 * @param <T>
	 * @param id
	 *            持久化Id
	 * @return true 成功删除一条数据,false删除失败
	 */
	public <T> boolean delById(int id, Class<T> c) {
		return this.delete(c, id) == 1;
	}

	/**
	 * 根据ID查询一个对象
	 * 
	 * @param <T>
	 * @param id
	 *            持久化Id
	 * @param c
	 *            要查询的表
	 * @return 查询到的对象
	 */
	public <T> T find(int id, Class<T> c) {
		return this.fetch(c, id);
	}

	/**
	 * 查询数据库中的全部数据
	 * 
	 * @param <T>
	 * @param c
	 *            查询的表
	 * @param orderby
	 *            desc 排序的条件
	 * @return List
	 */
	public <T> List<T> search(Class<T> c, String orderby) {
		return this.query(c, Cnd.orderBy().desc(orderby), null);

	}

	/**
	 * 根据条件查询数据库中满足条件的数据
	 * 
	 * @param <T>
	 * @param c
	 * @param condition
	 * @return
	 */
	public <T> List<T> search(Class<T> c, Condition condition) {
		return this.query(c, condition, null);

	}

	/**
	 * 分页查询表中所有数据
	 * 
	 * @param <T>
	 * @param c
	 *            查询的表
	 * @param currentPage
	 *            当前页数
	 * @param pageSize
	 *            每页显示数量
	 * @param orderby
	 *            desc排序的条件
	 * @return List
	 */
	public <T> List<T> searchByPage(Class<T> c, int currentPage, int pageSize, String orderby) {
		Pager pager = this.createPager(currentPage, pageSize);
		return this.query(c, Cnd.orderBy().desc(orderby), pager);
	}

	public <T> List<T> getPage(Class<T> c, int currentPage, int pageSize, String orderby) {
		Pager pager = this.createPager(currentPage, pageSize);
		List<T> list = this.query(c, Cnd.orderBy().desc(orderby), pager);
		return list;
	}

	/**
	 * 分页带条件查询所有数据
	 * 
	 * @param <T>
	 * @param c
	 *            查询的表
	 * @param condition
	 *            查询条件,用Cnd的静态方法构造
	 * @param currentPage
	 *            当前页码
	 * @param pageSize
	 *            每页显示的数据量
	 * @return List
	 */
	public <T> List<T> searchByPage(Class<T> c, Condition condition, int currentPage, int pageSize) {
		Pager pager = this.createPager(currentPage, pageSize);
		return this.query(c, condition, pager);
	}

	/**
	 * 增加一条数据
	 * 
	 * @param <T>
	 * @param t
	 * @return 返回增加到数据库的这条数据
	 */
	public <T> T save(T t) {
		return this.insert(t);
	}

	public void save(String table, Chain chain) {
		this.insert(table, chain);
	}

	/**
	 * 查询数据库中的数据条数
	 * 
	 * @param <T>
	 * @param c
	 *            查询的数据库表
	 * @return int
	 */
	public <T> int searchCount(Class<T> c) {
		return this.count(c);
	}

	/**
	 * 根据条件查询数据库中的数据条数
	 * 
	 * @param <T>
	 * @param c
	 *            查询的数据库表
	 * @param condition
	 *            条件,用Cnd的静态方法构造
	 * @return int
	 */
	public <T> int searchCount(Class<T> c, Condition condition) {
		return this.count(c, condition);
	}

	/**
	 * 计算最大分页数
	 * 
	 * @param count
	 *            记录总数
	 * @param pageSize
	 *            每页显示多少数据
	 * @return int
	 */
	public int maxPageSize(int count, int pageSize) {
		if (pageSize > 0) {
			if ((count % pageSize) != 0) {
				return (count / pageSize) + 1;
			} else {
				return (count / pageSize);
			}
		}
		return 0;
	}

	/**
	 * 根据多个id 查询数据
	 * 
	 * @param <T>
	 * @param ids
	 *            要查询的id,多个用","（逗号）分隔
	 * @param c
	 *            要查询的表信息
	 * @return List
	 */
	public <T> List<T> searchByIds(Class<T> c, String ids, String orderby) {
		Entity<T> entity = this.getEntity(c);

		String id = entity.getIdField().getColumnName();

		String sql = " " + id + " in (" + ids + ") order by " + orderby + " desc";

		return this.query(c, Cnd.wrap(sql), null);

	}

	/**
	 * 根据多个id 查询数据
	 * 
	 * @param <T>
	 * @param ids
	 *            整形的id数组
	 * @param c
	 *            要查询的表信息
	 * @return List
	 */
	public <T> List<T> searchByIds(Class<T> c, int[] ids, String orderby) {
		Entity<T> entity = this.getEntity(c);

		String id = entity.getIdField().getColumnName();

		return this.query(c, Cnd.where(id, "in", ids).desc(orderby), null);

	}

	/**
	 * 根据多个id删除数据
	 * 
	 * @param <T>
	 * @param c
	 *            要操作的表信息
	 * @param ids
	 *            要删除的id,多个用","（逗号）分隔
	 * @return true 成功,false 失败
	 */
	public <T> void deleteByIds(Class<T> c, String ids) {
		Entity<T> entity = this.getEntity(c);

		String table = entity.getTableName();

		String id = entity.getIdField().getColumnName();

		Sql sql = Sqls.create("delete from " + table + " where " + id + " in(" + ids + ")");

		this.execute(sql);
	}

	/**
	 * 根据条件返回一个条件
	 * 
	 * @param <T>
	 * @param condition
	 *            查询条件用Cnd构造
	 * @return T
	 */
	public <T> T findByCondition(Class<T> c, Condition condition) {
		return this.fetch(c, condition);
	}

	/**
	 * 模糊分页查询数据
	 * 
	 * @param <T>
	 * @param c
	 *            查询的数据库表
	 * @param value
	 *            查询的字段
	 * @param orderby
	 *            排序条件
	 * @param currentPage
	 *            当前页面
	 * @param pageSize
	 *            页面大小
	 * @return List
	 */
	public <T> List<T> searchPageByLike(Class<T> c, String value, String orderby, int currentPage, int pageSize) {
		Entity<T> entity = this.getEntity(c);

		List<MappingField> fields = entity.getMappingFields();

		SqlExpressionGroup group = null;

		for (MappingField f : fields) {
			if (!f.isId()) {
				SqlExpression e = Cnd.exp(f.getColumnName(), "LIKE", "%" + value + "%");
				if (group == null) {
					group = Cnd.exps(e);
				} else {
					group.or(e);
				}
			}
		}

		Pager pager = this.createPager(currentPage, pageSize);

		return this.query(c, Cnd.where(group).desc(orderby), pager);
	}

	/**
	 * 模糊分页查询数据记录总数
	 * 
	 * @param <T>
	 * @param c
	 *            查询的数据库表
	 * @param value
	 *            查询的字段
	 * @param orderby
	 *            排序条件
	 * @param currentPage
	 *            当前页面
	 * @param pageSize
	 *            页面大小
	 * @return List
	 */
	public <T> int searchPageByLike(Class<T> c, String value) {
		Entity<T> entity = this.getEntity(c);
		List<MappingField> fields = entity.getMappingFields();
		SqlExpressionGroup group = null;
		for (MappingField f : fields) {
			if (!f.isId()) {
				SqlExpression e = Cnd.exp(f.getColumnName(), "LIKE", "%" + value + "%");
				if (group == null) {
					group = Cnd.exps(e);
				} else {
					group.or(e);
				}
			}
		}

		return this.count(c, Cnd.where(group));
	}

	/**
	 * 根据指定的字段模糊分页查询数据
	 * 
	 * @param <T>
	 * @param c
	 *            查询的表
	 * @param fieldName
	 *            字段名称
	 * @param value
	 *            模糊条件
	 * @param currentPage
	 *            当前页码
	 * @param pageSize
	 *            每页数据量
	 * @return List
	 */
	public <T> List<T> searchByPageLike(Class<T> c, String fieldName, String value, int currentPage, int pageSize) {
		Entity<T> entity = this.getEntity(c);

		String column = entity.getField(fieldName).getColumnName();

		Pager pager = this.createPager(currentPage, pageSize);

		return this.query(c, Cnd.where(column, "LIKE", "%" + value + "%"), pager);

	}

	/**
	 * 根据指定的字段模糊分页查询数据 记录总数
	 * 
	 * @param <T>
	 * @param c
	 *            查询的表
	 * @param fieldName
	 *            字段名称
	 * @param value
	 *            模糊条件
	 * @param currentPage
	 *            当前页码
	 * @param pageSize
	 *            每页数据量
	 * @return List
	 */
	public <T> int searchByPageLike(Class<T> c, String fieldName, String value) {
		Entity<T> entity = this.getEntity(c);

		String column = entity.getField(fieldName).getColumnName();

		return this.count(c, Cnd.where(column, "LIKE", "%" + value + "%"));

	}

	/**
	 * 根据某个条件分页查询数据
	 * 
	 * @param <T>
	 * @param c
	 *            查询的表
	 * @param fieldName
	 *            匹配字段名
	 * @param value
	 *            匹配的值
	 * @param currentPage
	 *            当前页码
	 * @param pageSize
	 *            每页数据量
	 * @return List
	 */
	public <T> List<T> searchByPage(Class<T> c, String fieldName, String value, int currentPage, int pageSize) {
		Entity<T> entity = this.getEntity(c);

		String column = entity.getField(fieldName).getColumnName();

		Pager pager = this.createPager(currentPage, pageSize);
		return this.query(c, Cnd.where(column, "=", value), pager);
	}

	/**
	 * 根据指定条件返回一个对象
	 * 
	 * @param <T>
	 * @param fileName
	 *            匹配名称
	 * @param value
	 *            匹配值
	 * @return T
	 */
	public <T> T findByCondition(Class<T> c, String fileName, String value) {
		return this.fetch(c, Cnd.where(fileName, "=", value));
	}

	public <T> T findByCondition(Class<T> c, String fileName, String value, String fixreg) {
		T t = findByCondition(c, fileName, value);
		return this.fetchLinks(t, fixreg);
	}

	/**
	 * 添加一条数据到数据库中， 该数据包括关联的多个其他数据
	 * 
	 * @param <T>
	 * @param t
	 *            插入数据库的对象
	 * @param fieldName
	 *            关联数据的字段名，一般为List对象
	 * @return T
	 */
	public <T> T saveWidth(T t, String fieldName) {
		return this.insertWith(t, fieldName);

	}

	/**
	 * 获取关联对象
	 * 
	 * @param <T>
	 * @param t
	 *            查询的对象
	 * @param fieldName
	 *            关联的对象
	 * @return T
	 */
	public <T> T findLink(T t, String fieldName) {
		return this.fetchLinks(t, fieldName);
	}

	/**
	 * 更新自身和关联的对象
	 * 
	 * @param <T>
	 * @param t
	 *            修改的对象
	 * @param fieldName
	 *            关联对象
	 * @return T
	 */
	public <T> T updateWidth(T t, String fieldName) {
		return this.updateWith(t, fieldName);
	}

	/**
	 * 仅修改关联的对象的数据
	 * 
	 * @param <T>
	 * @param t
	 *            查询条件
	 * @param fieldName
	 *            修改的对象
	 * @return T
	 */
	public <T> T updateLink(T t, String fieldName) {
		return this.updateLinks(t, fieldName);
	}

	/**
	 * 删除自身和关联对象
	 * 
	 * @param <T>
	 * @param t
	 *            删除的对象
	 * @param fieldName
	 *            关联的对象
	 */
	public <T> void deleteWidth(T t, String fieldName) {
		this.deleteWith(t, fieldName);
	}

	/**
	 * 删除关联的对象，不删除自身
	 * 
	 * @param <T>
	 * @param t
	 *            删除的条件
	 * @param fieldName
	 *            删除的关联对象
	 */
	public <T> void deleteLink(T t, String fieldName) {
		this.deleteLinks(t, fieldName);
	}

	/**
	 * 保存对象的多对多 关系
	 * 
	 * @param <T>
	 * @param t
	 * @param fieldName
	 */
	public <T> T saveRelation(T t, String fieldName) {
		return this.insertRelation(t, fieldName);
	}

	/**
	 * 保存对象的关联关系,不保存对象本身
	 * 
	 * @param <T>
	 * @param t
	 * @param fieldName
	 * @return
	 */
	public <T> T saveLink(T t, String fieldName) {
		return this.insertLinks(t, fieldName);
	}

	/**
	 * 对于 '@One' 和 '@Many'，对应的记录将会删除 而 '@ManyMay' 对应的字段，只会清除关联表中的记录
	 * 
	 * @param <T>
	 * @param t
	 * @param fieldName
	 * @return
	 */
	public <T> T clearRelation(T t, String fieldName) {
		return this.clearLinks(t, fieldName);
	}

	/**
	 * 根据中间表分页查询数据
	 * 
	 * @param <T>
	 * @param c
	 *            查询主表
	 * @param joinTabel
	 *            中间表
	 * @param cloumnName
	 *            要获取中间表的字段
	 * @param condition
	 *            查询条件
	 * @param group
	 *            主查询条件组
	 * @param orderby
	 *            排序方式
	 * @param currentPage
	 *            当前页面
	 * @param pageSize
	 *            每页显示数据
	 * @return
	 */
	public <T> List<T> searchByRelation(Class<T> c, String joinTabel, String cloumnName, Condition condition, SqlExpressionGroup group, String orderby,
			int currentPage, int pageSize) {
		Entity<T> entity = this.getEntity(c);

		List<Record> records = this.query(joinTabel, condition, null);

		List<Integer> ids = new ArrayList<Integer>();
		for (Record r : records) {
			int id = r.getInt(cloumnName);
			ids.add(id);
		}
		if (ids.size() == 0) {
			return null;
		}

		Pager pager = this.createPager(currentPage, pageSize);

		SqlExpression e = Cnd.exp(entity.getIdField().getColumnName(), "in", Castors.me().castTo(ids, int[].class));

		ids = null;

		return this.query(c, Cnd.where(group.and(e)).desc(orderby), pager);
	}

	public List<Record> findListByCondition(String tableName, Condition condition) {
		List<Record> query = this.query(tableName, condition);
		return query;
	}

	public void execute(String sql) {
		super.execute(Sqls.create(sql));
	}
}