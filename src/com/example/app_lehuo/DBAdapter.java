package com.example.app_lehuo;

import java.sql.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
	/**
	 * 排队表的属性
	 */
	public static final String QUEUE_ID = "queueId";
	public static final String QUEUE_NUM = "queueNum";
	public static final String SHOP_ID = "shopId";
	public static final String USER_ID = "userId";
	public static final String MATERIAL_ID = "materialId";
	public static final String QUEUE_TIME = "time";
	public static final String TYPE = "type";

	/**
	 * 排队资源分类表的属性
	 */
	public static final String MATERIAL_NAME = "name";

	/**
	 * 实时评论表的属性
	 */
	public static final String RT_COMMENT_ID = "commentId";
	public static final String CONTENT = "content";
	public static final String COMMENT_TIME = "commentTime";
	public static final String GOOD_CNT = "gCnt";
	public static final String BAD_CNT = "bCnt";

	private static final String TAG = "DBAdapter";
	private static final String DATABASE_NAME = "lehuo";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_TABLE_QUEUE = "app_Queue";
	private static final String DATABASE_TABLE_MATERIALCLASSIFY = "app_MaterialClassify";
	private static final String DATABASE_TABLE_RTCOMMENT = "app_rtComment";

	private static final String DATABASE_CREATE =
	// 新建排队表
	"create table app_Queue ("
			+ "queueId integer primary key autoincrement, "
			+ "queueNum integer not null,"
			+ "shopId integer not null, "
			+ "userId integer not null,"
			+ "materialId integer not null,"
			+ "time text,"
			+ "type text default 0);"
			+

			// 新建排队资源分类表
			"create table app_MaterialClassify ("
			+ "materialId integer primary key autoincrement, "
			+ "name text not null);"
			+

			// 新建评论表
			"create table app_rtComment ("
			+ "commentId integer primary key autoincrement, "
			+ "content text not null, " + "commentTime text not null,"
			+ "gCnt int default 0," + "bCnt int default 0," + "shopId text, "
			+ "userId text);";

	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS titles");
			onCreate(db);
		}
	}

	/**
	 * 打开数据库
	 * 
	 * @return db
	 * @throws SQLException
	 */
	public DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	/**
	 * 关闭数据库
	 */
	public void close() {
		DBHelper.close();
	}

	/**
	 * 排队表插入
	 * 
	 * @param queueNum
	 * @param shopId
	 * @param userId
	 * @param materialId
	 * @param time
	 * @param type
	 * @return 插入的行号
	 */
	public long insertQueue(int queueNum, String shopId, String userId,
			int materialId, String time, String type) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(QUEUE_NUM, queueNum);
		initialValues.put(SHOP_ID, shopId);
		initialValues.put(USER_ID, userId);
		initialValues.put(MATERIAL_ID, materialId);
		initialValues.put(QUEUE_TIME, time);
		initialValues.put(TYPE, type);
		return db.insert(DATABASE_TABLE_QUEUE, null, initialValues);
	}

	/**
	 * 删除队列
	 * 
	 * @param rowId
	 * @return true or false
	 */
	public boolean deleteQueue(long rowId) {
		return db.delete(DATABASE_TABLE_QUEUE, QUEUE_ID + "=" + rowId, null) > 0;
	}

	/**
	 * 查找队列中的项
	 * 
	 * @param rowId
	 * @return cursor
	 * @throws SQLException
	 */
	public Cursor getQueueItem(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE_QUEUE, new String[] {
				QUEUE_ID, QUEUE_NUM, SHOP_ID, USER_ID, MATERIAL_ID, QUEUE_TIME,
				TYPE }, QUEUE_ID + "=" + rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	/**
	 * 更新队列
	 * 
	 * @param rowId
	 * @param queueNum
	 * @param shopId
	 * @param userId
	 * @param materialId
	 * @param date
	 * @param type
	 * @return true or false
	 */
	public boolean updateQueue(long rowId, int queueNum, String shopId,
			String userId, int materialId, String time, String type) {
		ContentValues args = new ContentValues();
		args.put(QUEUE_NUM, queueNum);
		args.put(SHOP_ID, shopId);
		args.put(USER_ID, userId);
		args.put(MATERIAL_ID, materialId);
		args.put(QUEUE_TIME, time);
		args.put(TYPE, type);
		return db.update(DATABASE_TABLE_QUEUE, args, QUEUE_ID + "=" + rowId,
				null) > 0;
	}

	/**
	 * 查询所有队列
	 * 
	 * @return cursor
	 */
	public Cursor getAllQueue() {
		return db.query(DATABASE_TABLE_QUEUE, new String[] { QUEUE_ID,
				QUEUE_NUM, SHOP_ID, USER_ID, MATERIAL_ID, QUEUE_TIME, TYPE },
				null, null, null, null, null);
	}

	public Cursor getQueueByMaterial(String materialName, int shopId, int userId) {
		Cursor cursor = db
				.rawQuery(
						"SELECT max(app_Queue.queueNum), min(app_Queue.queueNum) FROM app_Queue, app_MaterialClassify" +
						"where app_Queue.materialId = app_MaterialClassify.materialId and materialName =" + materialName
								+ " and shopId =" + shopId + " and userId =" + userId,
						new String[] { });

		/*
		 * db.query(true, DATABASE_TABLE_QUEUE DATABASE_TABLE_MATERIALCLASSIFY,
		 * new String[] { QUEUE_ID, QUEUE_NUM, SHOP_ID, USER_ID, MATERIAL_ID,
		 * QUEUE_TIME, TYPE }, MATERIAL_NAME + "=" + materialName, null, null,
		 * null, null, null);
		 */
		if (cursor != null) {
			cursor.moveToFirst();
		}
		return cursor;
	}

	/**************************************** 资源分类表 *************************************************/

	/**
	 * 插入资源分类表
	 * 
	 * @param materialId
	 * @param name
	 * @return
	 */
	public long insertMaterialClassify(int materialId, String name) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(MATERIAL_NAME, name);
		return db.insert(DATABASE_TABLE_MATERIALCLASSIFY, null, initialValues);
	}

	/**
	 * 删除资源分类
	 * 
	 * @param rowId
	 * @return true or false
	 */
	public boolean deleteMaterialClassify(long rowId) {
		return db.delete(DATABASE_TABLE_MATERIALCLASSIFY, MATERIAL_ID + "="
				+ rowId, null) > 0;
	}

	/**
	 * 查找队列中的项
	 * 
	 * @param rowId
	 * @return cursor
	 * @throws SQLException
	 */
	public Cursor getMaterialClassify(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE_MATERIALCLASSIFY,
				new String[] { MATERIAL_ID, MATERIAL_NAME }, MATERIAL_ID + "="
						+ rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	/**
	 * 更新资源分类
	 * 
	 * @param rowId
	 * @param name
	 * @return true or false
	 */
	public boolean updateMaterialClassify(long rowId, String name) {
		ContentValues args = new ContentValues();
		args.put(MATERIAL_NAME, name);
		return db.update(DATABASE_TABLE_MATERIALCLASSIFY, args, QUEUE_ID + "="
				+ rowId, null) > 0;
	}

	/**************************************** 实时评论 *************************************************/

	/**
	 * 插入实时评论
	 * 
	 * @param content
	 * @param commentTime
	 * @param gCnt
	 * @param bCnt
	 * @param userId
	 * @param shopId
	 * @return 返回row id
	 */
	public long insertRtComment(String content, String commentTime, int gCnt,
			int bCnt, String userId, String shopId) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(CONTENT, content);
		initialValues.put(COMMENT_TIME, commentTime);
		initialValues.put(GOOD_CNT, gCnt);
		initialValues.put(BAD_CNT, bCnt);
		initialValues.put(USER_ID, userId);
		initialValues.put(SHOP_ID, shopId);
		return db.insert(DATABASE_TABLE_RTCOMMENT, null, initialValues);
	}

	/**
	 * 删除实时评论
	 * 
	 * @param rowId
	 * @return true or false
	 */
	public boolean deleteRtComment(long rowId) {
		return db.delete(DATABASE_TABLE_RTCOMMENT, RT_COMMENT_ID + "=" + rowId,
				null) > 0;
	}

	/**
	 * 查找实时评论
	 * 
	 * @param rowId
	 * @return cursor
	 * @throws SQLException
	 */
	public Cursor getRtComment(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE_RTCOMMENT, new String[] {
				RT_COMMENT_ID, CONTENT, COMMENT_TIME, GOOD_CNT, BAD_CNT,
				USER_ID, SHOP_ID }, RT_COMMENT_ID + "=" + rowId, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	/**
	 * 更新实时评论
	 * 
	 * @param rowId
	 * @param content
	 * @param commentTime
	 * @param gCnt
	 * @param bCnt
	 * @param userId
	 * @param shopId
	 * @return true or false
	 */
	public boolean updateRtComment(long rowId, String content,
			String commentTime, int gCnt, int bCnt, String userId, String shopId) {
		ContentValues args = new ContentValues();
		args.put(CONTENT, content);
		args.put(COMMENT_TIME, commentTime);
		args.put(GOOD_CNT, gCnt);
		args.put(BAD_CNT, bCnt);
		args.put(USER_ID, userId);
		args.put(SHOP_ID, shopId);
		return db.update(DATABASE_TABLE_RTCOMMENT, args, RT_COMMENT_ID + "="
				+ rowId, null) > 0;
	}

}
