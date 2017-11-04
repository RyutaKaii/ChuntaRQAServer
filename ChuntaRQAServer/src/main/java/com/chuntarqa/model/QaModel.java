package com.chuntarqa.model;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.chuntarqa.dto.QaTable;


/**
 * QaModelクラス.<br>
 * RQaTableへのO/Rマッパー。<br>
 * 詳細はmapperを参照。
 * @author Ryuta
 *
 */
@Repository
public class QaModel {
	/** resource. */
	private static String resource = "mybatis-config.xml";

	/**
	 * select selectList.
	 * @return 結果
	 * @throws Exception
	 */
	public List<QaTable> selectList() throws Exception {
		try (Reader in = Resources.getResourceAsReader(resource)) {
			return new SqlSessionFactoryBuilder().build(in).openSession(true).selectList("qa.mybatis.selectList");
        } catch (IOException e) {
        	throw e;
        }
	}

	/**
	 * insert insertQa.
	 * @param qaTable
	 * @return 結果
	 * @throws Exception
	 */
	public int insertQa(QaTable qaTable) throws Exception {
		try (Reader in = Resources.getResourceAsReader(resource)) {
			return new SqlSessionFactoryBuilder().build(in).openSession(true).insert("qa.mybatis.insertQa", qaTable);
        } catch (IOException e) {
        	throw e;
        }
	}

	/**
	 * update updateQa.
	 * @param qaTable
	 * @return 結果
	 * @throws Exception
	 */
	public int updateQa(QaTable qaTable) throws Exception {
		try (Reader in = Resources.getResourceAsReader(resource)) {
			return new SqlSessionFactoryBuilder().build(in).openSession(true).update("qa.mybatis.updateQa", qaTable);
        } catch (IOException e) {
        	throw e;
        }
	}

	/**
	 * delete deleteQa.
	 * @param qaTable
	 * @return 結果
	 * @throws Exception
	 */
	public int deleteQa(QaTable qaTable) throws Exception {
		try (Reader in = Resources.getResourceAsReader(resource)) {
			return new SqlSessionFactoryBuilder().build(in).openSession(true).delete("qa.mybatis.deleteQa", qaTable);
        } catch (IOException e) {
        	throw e;
        }
	}

	/**
	 * selectOne selectQaOne.
	 * @param qaTable
	 * @return 結果
	 * @throws Exception
	 */
	public List<QaTable> selectQaOne(QaTable qaTable) throws Exception {
		try (Reader in = Resources.getResourceAsReader(resource)) {
			QaTable result = new SqlSessionFactoryBuilder().build(in).openSession(true).selectOne("qa.mybatis.selectQaOne", qaTable);

			List<QaTable> QaTableList = new ArrayList<QaTable>();
			QaTableList.add(result);

			return QaTableList;
        } catch (IOException e) {
        	throw e;
        }
	}

	/**
	 * select selectBetween.
	 * question
	 * @return 結果
	 * @throws Exception
	 */
	public List<QaTable> selectBetween(String from, String to) throws Exception {
		try (Reader in = Resources.getResourceAsReader(resource)) {
			return new SqlSessionFactoryBuilder().build(in).openSession(true).selectList("qa.mybatis.selectBetween",
					new SelectBetweenDto(from, to));
        } catch (IOException e) {
        	throw e;
        }
	}

	/**
	 * selectBetweenで用いるBeanクラス.
	 *
	 */
	public class SelectBetweenDto {
		int from;
		int to;

		public SelectBetweenDto(String from, String to) {
			this.from = Integer.parseInt(from);
			this.to= Integer.parseInt(to);
		}

		public int getFrom() {
			return from;
		}

		public void setFrom(int from) {
			this.from = from;
		}

		public int getTo() {
			return to;
		}

		public void setTo(int to) {
			this.to = to;
		}
	}

	/**
	 * select selectSearch.
	 * @return 結果
	 * @throws Exception
	 */
	public List<QaTable> selectSearch(QaTable qaTable) throws Exception {
		try (Reader in = Resources.getResourceAsReader(resource)) {
			return new SqlSessionFactoryBuilder().build(in).openSession(true).selectList("qa.mybatis.selectSearch", qaTable);
        } catch (IOException e) {
        	throw e;
        }
	}
}
