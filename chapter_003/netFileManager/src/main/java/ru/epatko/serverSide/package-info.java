/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 * 01.01.17.
 *
 * Задача 3.2.1. Сетевой менеджер файлов.
 * Перед реализацией в коде, составить каркас приложения на интерфейсах, с описанием.
 * 1. Разработать клиент-серверное приложение на сокетах.
 * 2. Серверная часть должна реализовывать следующее API:
 * - получить список корневого каталога. Корневой каталог задается при запуске сервера.
 * - перейти в подкаталог.
 * - спуститься в родительский каталог
 * - скачать файл
 * - загрузить файл.
 * 3. Клиент должен это апи уметь вызывать.
 * 4. Настройки портов и адреса считывать с app.properties
 *
 *
 * Server side of net file manager.
 */

package ru.epatko.serverSide;