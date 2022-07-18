$('document').ready(function() {
    $('table #editButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (category, status) {
            $('#categoryId').val(category.id);
            $('#categoryName').val(category.name);
        });
        $('#editCategoryModal').modal();
    })
})