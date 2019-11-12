**Instruction for start application**

1. You should to create container in docker or create DB in Postgresql. 
    
    - to created a container in Docker use the file `.yml` along the path 
    `/docker/docker-compose.yml`. 
    - To install postgresql, download the image file and follow the installation instructions to install on your local machine.
2. You should to create DB. For this action go to:

    - In the Database tool window (View | Tool Windows | Database), click the data source.
    - Open F4 or create (Ctrl+Shift+F10 | New Console) a database console.
    - Type or paste the statement that you want to execute depending on the base you are using.
    - Input user, password for your DB and press 'Test Connection'
    - Go to file `/resource/dump_db/createDbUsers.sql`
    - Press Ctrl+Enter. Alternatively, click the Execute icon 'Run' on the toolbar.
3. You should to create user table. For this action go to `/resources/dump_db/createTableUser.sql` and repeat command 'Execute'. Example item 'f'.
4. You should to create user_role table. For this action go to `/resources/dump_db/createTableUserRole.sql` and repeat command 'Execute'. Example item 'f'.
5. For start Project go to Application.class and press Run
