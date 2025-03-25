UI Layer
- UI Elements -> State Holders -> (Data Layer)

Data Layer
- Repositories -> Data Sources

UIState -> Repository -> DAO -> Room DB
DAO returns db objects

# Data Layer
Data sources		// actual database
Data repositories	// services to abstract access to data sources
Data models		// Represent data


## Database entity class -- Room
`@Entity` marks a class as a database entity class
`@PrimaryKey` marks a field as the primary key
- Every entity instance needs a primary key
Fields are represented as columns in db
`@ColumnInfo` allows to provide custom names for data class fields

```kotlin
@Entity(tableName = "example_table")
data class ExampleTableEntity (
	@PrimaryKey(autoGenerate = true) val uid: Int? = null,
	@ColumnInfo(name = "example_name" val exampleName: String?,
	val email: String? // this will just give the database field the same name as the val.
)
```

## Data Access Object provide CRUD functions for app
Expose database CRUD operations to the application, low level service used by
the application.

```kotlin
@Dao
interface ExampleDao {
	@Query("SELECT * FROM example_table")
	fun getAll(): List<ExampleTableEntity>

	@Insert
	fun add(user: LocalUser)
}
```

## Database class
Holds the database and is the main access point to the persisted data
- Defines the list of entities
- Provides the instances of DAOs

```kotlin
@Database(entities = [ExampleTableEntity::class], version = 1)
abstract class ExampleDatabase : RoomDatabase() {
	abstract fun exampleDao(): ExampleDao
}
```

- Use a singleton to instantiate the instance of the database, as we only want one instance
of the database in the application
- `object` keyword is used to create a singleton instance of a class.
- For Room database, we need to provide the context of the application (used to obtain information
about the application as room databasees are stored in a device directory specific to the app).
- By default database cannot run queries on the main thread, this can be disabled by calling
`.allowMainThreadQueries()` on the database instance returned by the `databaseBuilder` function.

```kotlin
object ExampleDatabase {
	fun getDatabase(context: Context) : ExampleDatabase {
		return Room.databaseBuilder(
			context,
			ExampleDatabase::class.java, "example_db"
		).allowMainThreadQueries()
	}
}
```

## Repository class
To access the database objects from the front end code, and additional repository class is provided.
This class abstracts the database itself, wrapping the interfaces provided by the dao objects specifically.

```kotlin
class ExampleRepository(private val exampleDao: ExampleDao) {
	fun getAll() : List<ExampleTableEntity> {
		return exampleDao.getAll()
	}
	fun add(user: ExampleTableEntity) {
		return exampleDao.add(user)
	}
}
```

# UI Layer
Need to now create state holder for application data.

## Business logic state holder
- Business logic state holder.
- Recall that at this stage it is appropriate for handling data validation.
- 

```kotlin
class ExampleUIState(private val repo: ExampleRepository) {
	// UI State example
	var users = repository.getAll().toMutableStateList()
	
	// Refreshing the business object state w/ repository data
	fun refresh() {
		// Use context function to act on the business state item
		users.apply {
			clear()
			addAll(repo.getAll())
		}
	}
	
	// Updating the database via the repository
	fun add(exampleTableEntity: ExampleTableEntity) {
		repo.add(exampleTableEntity)
	}
}
```

# Assemble!
Now it is necessary to assemble this infrastructure at the highest possible level of the application.
- Just inside the `MainActivity` function for our purposes

```kotlin
class MainActivity : ComponentActivity() {
	
	private val db by lazy { ExampleDatabase.getDatabase(applicationContext) }
	private val exampleRepo by lazy { ExampleRepository(db.exampleDao()) }
	
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			val exampleState = remember { ExampleUIState(exampleRepo) }
			Box(modifier = Modifier.safeDrawingPadding()) {
				MainContent(usersState)
			}
		}
	}
}
```




