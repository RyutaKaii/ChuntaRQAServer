package com.chuntarqa.model;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import com.chuntarqa.dto.UserTable;


/**
 * UserModelクラス.<br>
 * UserTableへのO/Rマッパー。<br>
 * 詳細はmapperを参照。
 * @author Chunta Web
 *
 */
@Repository
public class UserModel {
	/** resource. */
	private static String resource = "mybatis-config.xml";

	/**
	 * login.
	 * @param userTable
	 * @return 結果
	 * @throws Exception
	 */
	public List<UserTable> login(UserTable userTable) throws Exception {
		try (Reader in = Resources.getResourceAsReader(resource)) {
			return new SqlSessionFactoryBuilder().build(in).openSession(true).selectList("qa.mybatis.login", userTable);
        } catch (IOException e) {
        	throw e;
        }
	}

	/**
	 * select getResponselist.
	 * @param userTable
	 * @return 結果
	 * @throws Exception
	 */
	public List<UserTable> getResponselist(UserTable userTable) throws Exception {
		try (Reader in = Resources.getResourceAsReader(resource)) {
			return new SqlSessionFactoryBuilder().build(in).openSession(true).selectList("qa.mybatis.getResponselist", userTable);
        } catch (IOException e) {
        	throw e;
        }
	}

	/**
	 * update updateResponse.
	 * @param userTable
	 * @return 結果
	 * @throws Exception
	 */
	public int updateResponse(UserTable userTable) throws Exception {
		try (Reader in = Resources.getResourceAsReader(resource)) {
			return new SqlSessionFactoryBuilder().build(in).openSession(true).update("qa.mybatis.updateResponse", userTable);
        } catch (IOException e) {
        	throw e;
        }
	}

	/**
	 * select selectMail.
	 * @param userTable
	 * @return 結果
	 * @throws Exception
	 */
	public List<UserTable> selectMail() throws Exception {
		try (Reader in = Resources.getResourceAsReader(resource)) {
			return new SqlSessionFactoryBuilder().build(in).openSession(true).selectList("qa.mybatis.selectMail");
        } catch (IOException e) {
        	throw e;
        }
	}

	/**
	 * select selectCategory.
	 * @param userTable
	 * @return 結果
	 * @throws Exception
	 */
	public List<UserTable> selectCategory(UserTable userTable) throws Exception {
		try (Reader in = Resources.getResourceAsReader(resource)) {
			return new SqlSessionFactoryBuilder().build(in).openSession(true).selectList("qa.mybatis.selectCategory", userTable);
        } catch (IOException e) {
        	throw e;
        }
	}

}
