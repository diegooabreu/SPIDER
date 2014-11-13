@echo off
set caminhosql="C:\Users\Spider\Desktop\SPIDER_2"
mysql --port=3306 --user=root --password=spider < %caminhosql%\spider_RM.sql
mysql --port=3306 --user=root --password=spider<  %caminhosql%\spider_rm2.sql

