# Используем официальный образ Tomcat
FROM tomcat:latest

# Копируем WAR-архив в директорию развёртывания Tomcat
COPY psyho_help_project-1.0-SNAPSHOT.war /
