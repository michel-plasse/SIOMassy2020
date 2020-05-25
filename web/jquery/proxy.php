<?php

/** Proxy for calls to a remote resource, as a workaround of
 * Ajax limitations.
 * Query string parameters:
 *   url: remote url
 *   method : GET, PUT, POST ou DELETE (HTML knows only GET & POST,
 *   so we must set the method in a parameter)
 * Post parameters are forwarded to the remote resource.
 */
// Get the Requests library, far better than plain curl
require("C:\plasse\cours\php\Requests-1.6.0\library\Requests.php");
// Get its internal classes
Requests::register_autoloader();

if (empty($_GET["url"])) {
    die("Please set the URL url");
}
$url = $_GET["url"];
// Add to the url the query string parameters, url and method excepted
$params = array();
foreach ($_GET as $name => $value) {
    if ($name != "url" && $name != "method") {
        $params[] = "$name=$value";
    }
}
$queryString = implode("&", $params);
if (strlen($queryString) > 0) {
    $url = "$url?$queryString";
}

// Method
$method = "GET";
if (!empty($_GET["method"])) {
    $method = $_GET["method"];
}

// Potential parameters, if put, post, delete
$data = $_POST;

// Request headings. We forward only content-type,
// authorization and cookies.
// We should also forward Accept...
$headers = array();
$receivedHeaders = getallheaders();
if (array_key_exists("Authorization", $receivedHeaders)) {
    $headers["Authorization"] = $receivedHeaders["Authorization"];
}
if (array_key_exists("Cookies", $receivedHeaders)) {
    $headers["Cookies"] = $receivedHeaders["Cookies"];
}
if (array_key_exists("Content-Type", $receivedHeaders)) {
    $headers["Content-Type"] = $receivedHeaders["Content-Type"];
}

try {
    switch ($method) {
        case "GET":
            $response = Requests::get($url, $headers);
            break;
        case "POST":
            $response = Requests::post($url, $headers, $data);
            break;
        case "PUT":
            $response = Requests::put($url, $headers, $data);
            break;
        case "DELETE":
            $response = Requests::delete($url, $headers);
            break;
        default:
            die("method $method not implemented");
    }

// Send the WS status code
    header("HTTP/1.0 " . $response->status_code);
// Remove proxy headings and replace them by those got from the WS
    header_remove();
    foreach ($response->headers as $name => $value) {
        header("$name: $value");
    }
// Send finally the response body
    print $response->body;
} catch (Requests_Exception $exc) {
    header("HTTP/1.0 400 Bad request");
    header("Content-type: text/plain; charset=utf8");
    print $exc->getMessage();
}
?>