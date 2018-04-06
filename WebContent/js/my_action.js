function displayli(event) {
	$(event.target).parent("a").parent("li").children(".drop").css("display", "block");
}

function nodisplayli(event) {
	$(event.target).children(".drop").css("display", "none");
}