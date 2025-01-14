//<!DOCTYPE html>
//<html
//lang="en"
//xmlns:th="http://www.thymeleaf.org"
//class="h-full bg-white">
//<head>
//<title>Sign in to your account</title>
//</head>
//<body class="h-full">
//<style>
//body {
//    font-family: Arial, sans-serif;
//    background-color: #f8f8f8;
//}
//
//.container {
//    max-width: 400px;
//    margin: 0 auto;
//    padding: 20px;
//    background-color: #fff;
//    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
//}
//
//h1 {
//    text-align: center;
//    margin-bottom: 20px;
//}
//
//form {
//    display: flex;
//    flex-direction: column;
//    align-items: center;
//}
//
//label {
//    font-weight: bold;
//    margin-bottom: 10px;
//}
//
//input[type="text"], input[type="password"] {
//width: 100%;
//padding: 10px;
//margin-bottom: 20px;
//border: 1px solid #ccc;
//border-radius: 5px;
//}
//
//input[type="submit"] {
//background-color: #4CAF50;
//color: #fff;
//border: none;
//padding: 10px 20px;
//font-size: 16px;
//cursor: pointer;
//border-radius: 5px;
//box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
//}
//
//        .error {
//    color: red;
//    font-size: 14px;
//    margin-bottom: 10px;
//}
//</style>
//<div class="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
//        <h2 class="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Для продолжения необходима авторизация</h2>
//    </div>
//    <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
//
//        <div th:if="${param.error}" class="rounded-md bg-red-50 p-4">
//            <div class="flex">
//                <div class="ml-3">
//                    <h3 style="color:red" class="text-sm font-medium text-red-800">Неправильно введен логин или пароль</h3>
//                </div>
//            </div>
//        </div>
//
//        <div th:if="${param.logout}" class="rounded-md bg-green-50 p-4">
//            <div class="flex">
//                <div class="flex-shrink-0">
//                    <svg class="h-5 w-5 text-green-400" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
//                        <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.857-9.809a.75.75 0 00-1.214-.882l-3.483 4.79-1.88-1.88a.75.75 0 10-1.06 1.061l2.5 2.5a.75.75 0 001.137-.089l4-5.5z" clip-rule="evenodd" />
//                    </svg>
//                </div>
//                <div class="ml-3">
//                    <p class="text-sm font-medium text-green-800">You have been Successfully logged out</p>
//                </div>
//                <div class="ml-auto pl-3">
//                    <div class="-mx-1.5 -my-1.5">
//                        <button type="button" class="inline-flex rounded-md bg-green-50 p-1.5 text-green-500 hover:bg-green-100 focus:outline-none focus:ring-2 focus:ring-green-600 focus:ring-offset-2 focus:ring-offset-green-50">
//                            <span class="sr-only">Dismiss</span>
//                            <svg class="h-5 w-5" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
//                                <path d="M6.28 5.22a.75.75 0 00-1.06 1.06L8.94 10l-3.72 3.72a.75.75 0 101.06 1.06L10 11.06l3.72 3.72a.75.75 0 101.06-1.06L11.06 10l3.72-3.72a.75.75 0 00-1.06-1.06L10 8.94 6.28 5.22z" />
//                            </svg>
//                        </button>
//                    </div>
//                </div>
//            </div>
//        </div>
//
//        <form class="space-y-6" th:action="@{/login}" method="POST">
//            <div>
//                <label for="username" class="block text-sm font-medium leading-6 text-gray-900">Логин</label>
//                <div class="mt-2">
//                    <input id="username" name="username" type="text" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
//                </div>
//            </div>
//
//            <div>
//                <div class="flex items-center justify-between">
//                    <label for="password" class="block text-sm font-medium leading-6 text-gray-900">Пароль</label>
//                </div>
//                <div class="mt-2">
//                    <input id="password" name="password" type="password" autocomplete="current-password" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
//                </div>
//            </div>
//
//            <div>
//                <button type="submit" class="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Войти</button>
//            </div>
//        </form>
//            <form th:href="@{/users/new}">
//                <input type="submit" value="Зарегистрироваться">
//            </form>
//
//<!--        <td><a th:href="@{/users/new}"><span th:text="Зарегистрироваться"></span></a></td>-->
//    </div>
//</body>
//</html>