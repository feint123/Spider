package com.feint.utilities.factory;

import com.feint.utilities.DbUtils;
import com.feint.utilities.PluginUtils;
import com.mongodb.client.MongoDatabase;

/**
 * Created by feint on 17/6/17.
 */
public class MongoDbFactory {
    public static MongoDatabase create(){
        return DbUtils.getDatabase(
                PluginUtils.Database.URL,
                PluginUtils.Database.PORT,
                PluginUtils.Database.DATABASE_NAME);
    }
}
