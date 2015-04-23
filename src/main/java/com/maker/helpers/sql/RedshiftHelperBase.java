package com.maker.helpers.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;



public class RedshiftHelperBase {
	
	public static final String JDBC_DRIVER = "org.postgresql.Driver";
	public static final String DB_URL = "jdbc:postgresql://dw-beta.cydigjxbvckk.us-east-1.redshift.amazonaws.com:5439/maker?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
			//"jdbc:postgresql://dw-beta.cydigjxbvckk.us-east-1.redshift.amazonaws.com:5439/maker?ssl=true";
	

	// Database credentials
	private static final String USER = "awsconnect";
	private static final String PASS = "aws_Connect1";

	protected Connection conn;
	protected int rowsRead = -1;
	protected int rowsInserted = -1;
	protected int rowsUpdated = -1;
	protected long execTime = -1;
	protected long ttInRs = 0;

	public RedshiftHelperBase() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		long t = System.currentTimeMillis();
		
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("Connected to RedShift!");
		execTime = System.currentTimeMillis() - t;
		ttInRs +=execTime;
	}

	
	
	
	public void Updatetable(String cmsname, String type) 
			throws SQLException, InterruptedException {
		String sql=null;
		Statement stmt = conn.createStatement();
		
		Date dt= new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss a");
		
		sql = " update claimingdata."+type+" "
				+ " set cmsname = '"+cmsname+"' , report_lastupdated = '"+ft.format(dt)+"' where cmsname is null";
		
		stmt.execute(sql);
		
		stmt.close();
		
	}
	
	
	public void UpdatetableMonthly(String cmsname, String firstofthemonthinsert,String type) 
			throws SQLException, InterruptedException {
		String sql=null;
		
		
		Statement stmt = conn.createStatement();
		
		Date dt= new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss a");
		
		sql = " update claimingdata."+type+" "
				+ " set cmsname = '"+cmsname+"' , dim_date = '"+firstofthemonthinsert+"', report_lastupdated = '"+ft.format(dt)+"' where cmsname is null";
		
		stmt.execute(sql);
		
		stmt.close();
		
	}
	
	
	public void inserttablemonthly(String tablename) 
			throws SQLException, InterruptedException {
		String sql=null;
		
		
		Statement stmt = conn.createStatement();
		
		Set<String> setColsconv = new HashSet<String>();
		
		sql = "select  attname + case when typname <> 'varchar' then '::'+ case when typname = 'numeric' then typname+'(12,6)' else typname end else '' end as Colsconv "+
				" from pg_attribute a "+
				" join pg_class c "+
				" on a.attrelid = c.oid "+ 
				" join pg_namespace n "+
				" on n.oid = c.relnamespace "+
				" join pg_type t "+
				" on t.oid = a.atttypid "+
				" where c.relname not like '%pkey' "+
				" and n.nspname not like 'pg%' "+
				" and n.nspname not like 'information%' "+
				" and attnum > 0 "+
				" and nspname = 'claimingdata' "+
				" and c.relname = 'asset_report_monthly' "+
				" and attname in ( "+
				" select  attname  "+
				" from pg_attribute a "+
				" join pg_class c "+
				" on a.attrelid = c.oid "+ 
				" join pg_namespace n "+
				" on n.oid = c.relnamespace "+
				" where c.relname not like '%pkey' "+
				" and n.nspname not like 'pg%' "+
				" and n.nspname not like 'information%' "+
				" and attnum > 0 "+
				" and nspname = 'claimingdata' "+
				" and c.relname ilike '"+tablename+"' ) ";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
	     {
				setColsconv.add(rs.getString("Colsconv"));
	             
	     }
	     rs.close();
		
	     	     
	     String Columnlist = setColsconv.toString().substring(1, setColsconv.toString().length()-1);
	     String ColumnlistConv = Columnlist.replace("::date","").replace("::int4","").replace("::int8","").replace("::numeric(12,6)","");
			
		sql = " insert into claimingdata.asset_report_monthly ("+ColumnlistConv+")  "
				+ " select "+Columnlist+" "
				+ " from claimingdata."+tablename;
		
		System.out.println(sql);
		stmt.execute(sql);
		
		sql = " drop table if exists claimingdata."+tablename;
		
		stmt.execute(sql);
		
		
		
		
		stmt.close();
		
	}
	
	
	public void inserttableclaim(String tablename) 
			throws SQLException, InterruptedException {
		String sql=null;
		
		
		Statement stmt = conn.createStatement();
		
		Set<String> setColsconv = new HashSet<String>();
		
		sql = "select  attname + case when typname <> 'varchar' then '::'+ case when typname = 'numeric' then typname+'(12,6)' else typname end else '' end as Colsconv "+
				" from pg_attribute a "+
				" join pg_class c "+
				" on a.attrelid = c.oid "+ 
				" join pg_namespace n "+
				" on n.oid = c.relnamespace "+
				" join pg_type t "+
				" on t.oid = a.atttypid "+
				" where c.relname not like '%pkey' "+
				" and n.nspname not like 'pg%' "+
				" and n.nspname not like 'information%' "+
				" and attnum > 0 "+
				" and nspname = 'claimingdata' "+
				" and c.relname = 'claimed_report' "+
				" and attname in ( "+
				" select  attname  "+
				" from pg_attribute a "+
				" join pg_class c "+
				" on a.attrelid = c.oid "+ 
				" join pg_namespace n "+
				" on n.oid = c.relnamespace "+
				" where c.relname not like '%pkey' "+
				" and n.nspname not like 'pg%' "+
				" and n.nspname not like 'information%' "+
				" and attnum > 0 "+
				" and nspname = 'claimingdata' "+
				" and c.relname ilike '"+tablename+"' ) ";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
	     {
				setColsconv.add(rs.getString("Colsconv"));
	             
	     }
	     rs.close();
		
	     	     
	     String Columnlist = setColsconv.toString().substring(1, setColsconv.toString().length()-1);
	     String ColumnlistConv = Columnlist.replace("::date","").replace("::int4","").replace("::int8","").replace("::numeric(12,6)","");
			
		sql = " insert into claimingdata.claimed_report ("+ColumnlistConv+")  "
				+ " select "+Columnlist+" "
				+ " from claimingdata."+tablename;
		
		System.out.println(sql);
		stmt.execute(sql);
		
		sql = " drop table if exists claimingdata."+tablename;
		
		stmt.execute(sql);
		
		
		
		
		stmt.close();
		
	}
	
	
	
	public void inserttableasset(String tablename) 
			throws SQLException, InterruptedException {
		String sql=null;
		
		
		Statement stmt = conn.createStatement();
		
		Set<String> setColsconv = new HashSet<String>();
		
		sql = "select  attname + case when typname <> 'varchar' then '::'+ case when typname = 'numeric' then typname+'(12,6)' else typname end else '' end as Colsconv "+
				" from pg_attribute a "+
				" join pg_class c "+
				" on a.attrelid = c.oid "+ 
				" join pg_namespace n "+
				" on n.oid = c.relnamespace "+
				" join pg_type t "+
				" on t.oid = a.atttypid "+
				" where c.relname not like '%pkey' "+
				" and n.nspname not like 'pg%' "+
				" and n.nspname not like 'information%' "+
				" and attnum > 0 "+
				" and nspname = 'claimingdata' "+
				" and c.relname = 'asset_report' "+
				" and attname in ( "+
				" select  attname  "+
				" from pg_attribute a "+
				" join pg_class c "+
				" on a.attrelid = c.oid "+ 
				" join pg_namespace n "+
				" on n.oid = c.relnamespace "+
				" where c.relname not like '%pkey' "+
				" and n.nspname not like 'pg%' "+
				" and n.nspname not like 'information%' "+
				" and attnum > 0 "+
				" and nspname = 'claimingdata' "+
				" and c.relname ilike '"+tablename+"' ) ";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
	     {
				setColsconv.add(rs.getString("Colsconv"));
	             
	     }
	     rs.close();
		
	     	     
	     String Columnlist = setColsconv.toString().substring(1, setColsconv.toString().length()-1);
	     String ColumnlistConv = Columnlist.replace("::date","").replace("::int4","").replace("::int8","").replace("::numeric(12,6)","");
			
		sql = " insert into claimingdata.asset_report ("+ColumnlistConv+")  "
				+ " select "+Columnlist+" "
				+ " from claimingdata."+tablename;
		
		System.out.println(sql);
		stmt.execute(sql);
		
		sql = " drop table if exists claimingdata."+tablename;
		
		stmt.execute(sql);
		
		
		
		
		stmt.close();
		
	}
	
	
	public void populatecolumns() 
			throws SQLException, InterruptedException {
		String sql=null;
		
		Statement stmt = conn.createStatement();
		
		Set<String> setCols = new HashSet<String>();
		Set<String> setColsconv = new HashSet<String>();
		
		sql = "select  attname as Cols,attname + case when typname <> 'varchar' then '::'+ case when typname = 'numeric' then typname+'(12,6)' else typname end else '' end as Colsconv "+
				" from pg_attribute a "+
				" join pg_class c "+
				" on a.attrelid = c.oid "+ 
				" join pg_namespace n "+
				" on n.oid = c.relnamespace "+
				" join pg_type t "+
				" on t.oid = a.atttypid "+
				" where c.relname not like '%pkey' "+
				" and n.nspname not like 'pg%' "+
				" and n.nspname not like 'information%' "+
				" and attnum > 0 "+
				" and nspname = 'claimingdata' "+
				" and c.relname = 'asset_report_monthly' "+
				" and attname in ( "+
				" select  attname  "+
				" from pg_attribute a "+
				" join pg_class c "+
				" on a.attrelid = c.oid "+ 
				" join pg_namespace n "+
				" on n.oid = c.relnamespace "+
				" where c.relname not like '%pkey' "+
				" and n.nspname not like 'pg%' "+
				" and n.nspname not like 'information%' "+
				" and attnum > 0 "+
				" and nspname = 'claimingdata' "+
				" and c.relname ilike 'WaltDisneyMusicCo_Pub_YouTube_WaltDisneyMusicCo_Pub_M_20140701_20140731_rev_views_by_asset' ) order by 1 ";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
	     {
			//System.out.println("Adding Cols : "+rs.getString("Cols"));
					setCols.add(rs.getString("Cols"));
			//System.out.println("Adding Cols : "+rs.getString("Colsconv"));
					setColsconv.add(rs.getString("Colsconv"));
	             
	     }
	     rs.close();
		
	     System.out.println(setColsconv);
	     System.out.println(setColsconv.toString().replace("::date","").replace("::int4","").replace("::int8","").replace("::numeric(12,6)",""));
	     
	     
		stmt.close();
		
	}
	
	
	 public Set<String> GetCMSvalues() 
             throws SQLException, InterruptedException {
     String sql=null;
     
     Set<String> set = new HashSet<String>();
     sql = "select cmsname,account from YouTubeBatchReports.CMSDetails --where cmsname in ('WaltDisneyMusicCo_Pub') order by LoadOrder ";
     //where cmsname = 'utvmotionpictures' --loadorder = 1 
     //System.out.println(sql);
     //'WaltDisneyMusicCo_Pub','Maker_Studios_5','Disney_Content_Management','MarvelEntertainment'
     //'H7XeNNPkVV3JZxXm-O-MCA','rumblefish','rumblefish_cdbaby','take180com','RPMNetworks'
     Statement stmt = conn.createStatement();
     
     
     ResultSet rs = stmt.executeQuery(sql);
while(rs.next())
     {
			String cmsname = rs.getString("cmsname");
			String account = rs.getString("account");
		    set.add(cmsname + " account : " + account );  
             
     }
     rs.close();
     stmt.close();
     return set;
}
	
	 public long importCSVIntoTableMonthly(String MonthlyAssetheaders,String csvFile,String tablename, String CMS,String firstofthemonthinsert) throws SQLException {
			
			
			Statement stmt = conn.createStatement();
			
			
			String sql = "delete claimingdata.asset_report_monthly where cmsname = '"+CMS+"' and dim_date = '"+firstofthemonthinsert+"' ";
			
			stmt.execute(sql);
			
			
			//StringBuilder sb = new StringBuilder();
			long t1 = System.currentTimeMillis();
			String sqlTemplate =
					"copy claimingdata.asset_report_monthly (asset_id, asset_labels, asset_channel_id, custom_id, asset_title, country, total_views, ad_enabled_views, ad_requested_views, watch_page_views, embedded_page_views, channel_page_video_views, gross_youtube_sold_revenue, gross_partner_sold_revenue, gross_adsense_sold_revenue, total_earnings, net_youtube_sold_revenue, net_adsense_sold_revenue) " +
							" FROM 's3://maker_analytics/shadow_youtube/{CSV_FILE}' " + 
							" credentials 'aws_access_key_id=AKIAJ4DANELWDV4P6QRA;aws_secret_access_key=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' " + 
							" delimiter ',' " + 
							" removequotes " + 
							" MAXERROR 50000 " + 
							" ignoreheader 1";
			
			String sqlCopy = sqlTemplate.replace("{CSV_FILE}", csvFile);
			
			
			int ret=-1;
			boolean copySuccessful = false;
			int tries = 0;
			long sleepTime = 1000;
			do {
				try {
					tries++;
					if (tries==2) sleepTime = 1000 ; // 5 minutes wait
					if (tries==3) sleepTime = 1000 ; // 20 minutes wait
					ret = stmt.executeUpdate(sqlCopy);
					copySuccessful = true;
				} catch(SQLException e) {
					try { Thread.sleep(sleepTime); } catch(InterruptedException e1) {};
				}
			} while (!copySuccessful && tries<=3);
			execTime = (System.currentTimeMillis()-t1);
			ttInRs +=execTime;
			if (copySuccessful) {
				return execTime;
			}
			
			return -1;
		}
	
	 
	 public long importCSVIntoTableMonthlydiff(String MonthlyAssetheaders,String csvFile,String tablename, String CMS,String firstofthemonthinsert) throws SQLException {
			
			
			Statement stmt = conn.createStatement();
			
			String sql = "delete claimingdata.asset_report_monthly where cmsname = '"+CMS+"' and dim_date = '"+firstofthemonthinsert+"' ";
			
			stmt.execute(sql);
			
			sql = "drop table if exists claimingdata."+tablename; 
			
			stmt.execute(sql);		
			
			
			stmt.execute(MonthlyAssetheaders);		
			
			
			//StringBuilder sb = new StringBuilder();
			long t1 = System.currentTimeMillis();
			String sqlTemplate =
					"copy claimingdata."+tablename+
							" FROM 's3://maker_analytics/shadow_youtube/{CSV_FILE}' " + 
							" credentials 'aws_access_key_id=AKIAJ4DANELWDV4P6QRA;aws_secret_access_key=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' " + 
							" delimiter ',' " + 
							" removequotes " + 
							" MAXERROR 50000 " + 
							" ignoreheader 1";
			
			String sqlCopy = sqlTemplate.replace("{CSV_FILE}", csvFile);
			
			
			int ret=-1;
			boolean copySuccessful = false;
			int tries = 0;
			long sleepTime = 1000;
			do {
				try {
					tries++;
					if (tries==2) sleepTime = 1000 ; // 5 minutes wait
					if (tries==3) sleepTime = 1000 ; // 20 minutes wait
					ret = stmt.executeUpdate(sqlCopy);
					copySuccessful = true;
				} catch(SQLException e) {
					try { Thread.sleep(sleepTime); } catch(InterruptedException e1) {};
				}
			} while (!copySuccessful && tries<=3);
			execTime = (System.currentTimeMillis()-t1);
			ttInRs +=execTime;
			if (copySuccessful) {
				return execTime;
			}
			
			return -1;
		}
	 
	 
	 
	 public long importCSVIntoTableClaimdiff(String MonthlyAssetheaders,String csvFile,String tablename, String CMS) throws SQLException {
			
			
			Statement stmt = conn.createStatement();
			
			String sql = "delete claimingdata.claimed_report where cmsname = '"+CMS+"' ";
			
			stmt.execute(sql);		
			
			sql = "drop table if exists claimingdata."+tablename; 
				
			stmt.execute(sql);
			
			stmt.execute(MonthlyAssetheaders);
			
			//StringBuilder sb = new StringBuilder();
			long t1 = System.currentTimeMillis();
			String sqlTemplate =
					"copy claimingdata."+tablename+
							" FROM 's3://maker_analytics/shadow_youtube/{CSV_FILE}' " + 
							" credentials 'aws_access_key_id=AKIAJ4DANELWDV4P6QRA;aws_secret_access_key=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' " + 
							" delimiter ',' " + 
							" removequotes " + 
							" MAXERROR 50000 " + 
							" ignoreheader 1";
			
			String sqlCopy = sqlTemplate.replace("{CSV_FILE}", csvFile);
			
			
			int ret=-1;
			boolean copySuccessful = false;
			int tries = 0;
			long sleepTime = 1000;
			do {
				try {
					tries++;
					if (tries==2) sleepTime = 1000 ; // 5 minutes wait
					if (tries==3) sleepTime = 1000 ; // 20 minutes wait
					ret = stmt.executeUpdate(sqlCopy);
					copySuccessful = true;
				} catch(SQLException e) {
					try { Thread.sleep(sleepTime); } catch(InterruptedException e1) {};
				}
			} while (!copySuccessful && tries<=3);
			execTime = (System.currentTimeMillis()-t1);
			ttInRs +=execTime;
			if (copySuccessful) {
				return execTime;
			}
			
			return -1;
		}
	 
	 
	public long importCSVIntoTableClaim(String csvFile,String tablename, String CMS) throws SQLException {
		
		
		Statement stmt = conn.createStatement();
		
		String sql = "delete claimingdata.claimed_report where cmsname = '"+CMS+"' ";
		
		stmt.execute(sql);		
		
		
		//StringBuilder sb = new StringBuilder();
		long t1 = System.currentTimeMillis();
		String sqlTemplate =
				"copy claimingdata.claimed_report (video_id, claim_id, asset_id, video_uploader, channel_id, channel_display_name, video_title, video_views, status, claim_origin, claim_type, is_partner_uploaded, is_premium, reference_video_id, applied_policy, claim_date, video_upload_date, custom_id, title, notes, asset_labels ) "+
						" FROM 's3://maker_analytics/shadow_youtube/{CSV_FILE}' " + 
						" credentials 'aws_access_key_id=AKIAJ4DANELWDV4P6QRA;aws_secret_access_key=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' " + 
						" delimiter ',' " + 
						" removequotes " + 
						" MAXERROR 50000 " + 
						" ignoreheader 1";
		
		String sqlCopy = sqlTemplate.replace("{CSV_FILE}", csvFile);
		
		
		int ret=-1;
		boolean copySuccessful = false;
		int tries = 0;
		long sleepTime = 1000;
		do {
			try {
				tries++;
				if (tries==2) sleepTime = 1000 ; // 5 minutes wait
				if (tries==3) sleepTime = 1000 ; // 20 minutes wait
				ret = stmt.executeUpdate(sqlCopy);
				copySuccessful = true;
			} catch(SQLException e) {
				try { Thread.sleep(sleepTime); } catch(InterruptedException e1) {};
			}
		} while (!copySuccessful && tries<=3);
		execTime = (System.currentTimeMillis()-t1);
		ttInRs +=execTime;
		if (copySuccessful) {
			return execTime;
		}
		
		return -1;
	}

	public long importCSVIntoTableAssetdiff(String MonthlyAssetheaders,String csvFile,String tablename, String CMS) throws SQLException {


		Statement stmt = conn.createStatement();
		
		String sql = "delete claimingdata.asset_report where cmsname = '"+CMS+"' ";
		
		stmt.execute(sql);	
		
		sql = "drop table if exists claimingdata."+tablename; 
			
		stmt.execute(sql);
		
		stmt.execute(MonthlyAssetheaders);
		
		
		//StringBuilder sb = new StringBuilder();
		long t1 = System.currentTimeMillis();
		String sqlTemplate =
				"copy claimingdata."+tablename+
						" FROM 's3://maker_analytics/shadow_youtube/{CSV_FILE}' " + 
						" credentials 'aws_access_key_id=AKIAJ4DANELWDV4P6QRA;aws_secret_access_key=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' " + 
						" delimiter ',' " + 
						" removequotes " + 
						" MAXERROR 50000 " + 
						" ignoreheader 1";
		
		String sqlCopy = sqlTemplate.replace("{CSV_FILE}", csvFile);
		
		
		int ret=-1;
		boolean copySuccessful = false;
		int tries = 0;
		long sleepTime = 1000;
		do {
			try {
				tries++;
				if (tries==2) sleepTime = 1000 ; // 5 minutes wait
				if (tries==3) sleepTime = 1000 ; // 20 minutes wait
				ret = stmt.executeUpdate(sqlCopy);
				copySuccessful = true;
			} catch(SQLException e) {
				try { Thread.sleep(sleepTime); } catch(InterruptedException e1) {};
			}
		} while (!copySuccessful && tries<=3);
		execTime = (System.currentTimeMillis()-t1);
		ttInRs +=execTime;
		if (copySuccessful) {
			return execTime;
		}
		
		return -1;
	}
	public long importCSVIntoTableAsset(String csvFile,String tablename, String CMS) throws SQLException {


		Statement stmt = conn.createStatement();
		
		String sql = "delete claimingdata.asset_report where cmsname = '"+CMS+"' ";
		
		stmt.execute(sql);	
		
		//StringBuilder sb = new StringBuilder();
		long t1 = System.currentTimeMillis();
		String sqlTemplate =
				"copy claimingdata.asset_report (recent_daily_average, asset_id, asset_type, status, metadata_origination, custom_id, season_number, episode_title, tms, number_of_claims, constituent_asset_ids, active_reference_ids, inactive_reference_ids, match_policy, is_merged_asset_yes_no_, conflicting_owner, ownership, asset_labels) "+
						" FROM 's3://maker_analytics/shadow_youtube/{CSV_FILE}' " + 
						" credentials 'aws_access_key_id=AKIAJ4DANELWDV4P6QRA;aws_secret_access_key=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' " + 
						" delimiter ',' " + 
						" removequotes " + 
						" MAXERROR 50000 " + 
						" ignoreheader 1";
		
		String sqlCopy = sqlTemplate.replace("{CSV_FILE}", csvFile);
		
		
		int ret=-1;
		boolean copySuccessful = false;
		int tries = 0;
		long sleepTime = 1000;
		do {
			try {
				tries++;
				if (tries==2) sleepTime = 1000 ; // 5 minutes wait
				if (tries==3) sleepTime = 1000 ; // 20 minutes wait
				ret = stmt.executeUpdate(sqlCopy);
				copySuccessful = true;
			} catch(SQLException e) {
				try { Thread.sleep(sleepTime); } catch(InterruptedException e1) {};
			}
		} while (!copySuccessful && tries<=3);
		execTime = (System.currentTimeMillis()-t1);
		ttInRs +=execTime;
		if (copySuccessful) {
			return execTime;
		}
		
		return -1;
	}
	
	public void close() {
		try {
			if (conn!=null) {
				conn.close();
				conn=null;
			}
		} catch (SQLException e) {
			System.out.println("database close exception!");
		}
	}
	
	public int getRowsRead() {
		return rowsRead;
	}

	public void setRowsRead(int rowsRead) {
		this.rowsRead = rowsRead;
	}

	public int getRowsInserted() {
		return rowsInserted;
	}

	public void setRowsInserted(int rowsInserted) {
		this.rowsInserted = rowsInserted;
	}

	public int getRowsUpdated() {
		return rowsUpdated;
	}

	public void setRowsUpdated(int rowsUpdated) {
		this.rowsUpdated = rowsUpdated;
	}

	public long getExecTime() {
		return execTime;
	}

	public void setExecTime(long execTime) {
		this.execTime = execTime;
	}

	public long getTotalTimeInRedShift() {
		return ttInRs;
	}

	public void setTotalTimeInRedShift(long ttInRs) {
		this.ttInRs = ttInRs;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
