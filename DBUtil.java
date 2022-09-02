package qms.utilities;

import java.sql.SQLException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

public class DBUtil {
	
	/**
	 * Convert the ResultSet to a JsonArray having JsonObjects, where each field-key of JsonObject denotes the DB column name
	 * @param RowSet
	 * @return JsonArray
	 */
	

	public static JsonArray resultToArray(RowSet<Row> rows) {
		JsonArray result_array = new JsonArray();
		int columns = rows.columnDescriptors().size();
		
		for (Row row : rows) {
		      JsonObject temp_obj=new JsonObject();
		      for(int i = 0; i < columns; ++i){
		    	  if(rows.columnDescriptors().get(i).typeName().contains("TIMESTAMP"))
		    		  temp_obj.put(rows.columnDescriptors().get(i).name(), row.getLocalDateTime(i).toString());
		    	  else if(rows.columnDescriptors().get(i).typeName().contains("DATE"))
		    		  temp_obj.put(rows.columnDescriptors().get(i).name(), row.getLocalDate(i).toString());
		    	  else
		    		  temp_obj.put(rows.columnDescriptors().get(i).name(), row.getValue(i));
		        }
		      result_array.add(temp_obj);
		    }
		return result_array;
	}

}
