#!/bin/bash
DATE=$(date +%Y-%m-%dT%H%M%S)
DATABASE="fieldservice"
FILENAME=$DATABASE"_"$DATE  
echo Backupped: $FILENAME
cd "$(dirname "$0")"
pg_dump -U postgres fieldservice -f db_backups/$FILENAME.sql
pg_dump -U postgres -Ft fieldservice -f db_backups/$FILENAME.tar
exit
