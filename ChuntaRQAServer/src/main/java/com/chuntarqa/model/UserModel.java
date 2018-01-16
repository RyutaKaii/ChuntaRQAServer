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
			return new SqlSessionFactoryBuilder().build(in).openSession(true).selectList("user.mybatis.login", userTable);
        } catch (IOException e) {
        	throw e;
        }
	}

	/**
	 * selectMail.
	 * @param userTable
	 * @return 結果
	 * @throws Exception
	 */
	public List<UserTable> selectMail() throws Exception {
		try (Reader in = Resources.getResourceAsReader(resource)) {
			return new SqlSessionFactoryBuilder().build(in).openSession(true).selectList("user.mybatis.selectMail");
        } catch (IOException e) {
        	throw e;
        }
	}

	/**
	 * selectCategory.
	 * @param userTable
	 * @return 結果
	 * @throws Exception
	 */
	public List<UserTable> selectCategory(UserTable userTable) throws Exception {
		try (Reader in = Resources.getResourceAsReader(resource)) {
			return new SqlSessionFactoryBuilder().build(in).openSession(true).selectList("user.mybatis.selectCategory", userTable);
        } catch (IOException e) {
        	throw e;
        }
	}
}
